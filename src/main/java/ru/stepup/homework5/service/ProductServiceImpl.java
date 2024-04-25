package ru.stepup.homework5.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.homework5.dto.AgreesmentRequest;
import ru.stepup.homework5.dto.ProductRequest;
import ru.stepup.homework5.dto.ProductResponse;
import ru.stepup.homework5.entity.Agreement;
import ru.stepup.homework5.entity.TppProduct;
import ru.stepup.homework5.entity.TppProductRegister;
import ru.stepup.homework5.enums.States;
import ru.stepup.homework5.exception.ExemplNotFoundException;
import ru.stepup.homework5.exception.FindDublException;
import ru.stepup.homework5.repo.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private final TppProductRepository productRepository;
    @Autowired
    private final AgreesmentRepository agreesmentRepository;
    @Autowired
    private final TppRefProductRegisterTypeRepository registerTypeRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final TppProductRegisterRepository registerRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        List<String> productIdRegisterList = new ArrayList<>();
        List<String> agreementIdList = new ArrayList<>();
        Integer productId = null;
        if (productRequest.instanceId() == null) {
            var checkProductDubl = productRepository.findByNum(productRequest.contractNumber());
            if (checkProductDubl.isPresent()) {
                throw new FindDublException("Параметр ContractNumber № договора "
                        + productRequest.contractNumber()
                        + " уже существует для ЭП с ИД "
                        + checkProductDubl.get().getId()
                );
            }

            for (AgreesmentRequest agr: productRequest.instanceArrangement()) {
                var checkAgreementDubl = agreesmentRepository.findByNum(agr.number());
                if (checkAgreementDubl.isPresent()) {
                    throw new FindDublException("Параметр № Дополнительного соглашения (сделки) Number "
                            + agr.number()
                            + " уже существует для ЭП с ИД "
                            + checkAgreementDubl.get().getTppProduct().getId()
                    );
                }
            }

            var registerType = registerTypeRepository.findByProductClassValue(productRequest.productCode());
            if (registerType.isEmpty()) {
                throw new ExemplNotFoundException(
                        "Код Продукта "
                        + productRequest.productCode()
                        + " не найдено в Каталоге продуктов tpp_ref_product_class"
                );
            }

            TppProduct product = new TppProduct();
            product.setProductCodeId(productRequest.contractId());
            product.setType(productRequest.productType());
            product.setClient_id(Integer.valueOf(productRequest.mdmCode()));
            product.setNumber(productRequest.contractNumber());

            var resProduct = productRepository.save(product);
            productId = resProduct.getId();

            for (AgreesmentRequest agr: productRequest.instanceArrangement()) {
                Agreement agreement = new Agreement();
                agreement.setGeneralAgreementId(agr.generalAgreementId());
                agreement.setNumber(agr.number());
                agreement.setTppProduct(resProduct);
                var agreesmentRes = agreesmentRepository.save(agreement);
                agreementIdList.add(String.valueOf(agreesmentRes.getId()));
            }

            var account = accountRepository.getAccount(
                    productRequest.branchCode(),
                    productRequest.isoCurrencyCode(),
                    productRequest.mdmCode(),
                    productRequest.urgencyCode(),
                    productRequest.registerType()
            );

            TppProductRegister register = new TppProductRegister();
            register.setRegisterType(registerType.get());
            register.setProductId(productId);
            register.setAccount(account.get().getId());
            register.setState(States.OPEN);
            register.setAccountNumber(account.get().getAccountNumber());

            var resRegister = registerRepository.save(register);
            productIdRegisterList.add(String.valueOf(resRegister.getId()));


        } else {
            var product = productRepository.findById(productRequest.instanceId());
            if (product.isEmpty()) {
                throw new ExemplNotFoundException(
                        "Экземпляр продукта с параметром instanceId "
                                + productRequest.instanceId()
                                + " не найден"
                );
            }
            productId = productRequest.instanceId();

            for (AgreesmentRequest agr: productRequest.instanceArrangement()) {
                var checkAgreementDubl = agreesmentRepository.findByNum(agr.number());
                if (checkAgreementDubl.isPresent()) {
                    throw new FindDublException("Параметр № Дополнительного соглашения (сделки) Number "
                            + agr.number()
                            + " уже существует для ЭП с ИД "
                            + productRequest.instanceId()
                    );
                }
            }

            for (AgreesmentRequest agr: productRequest.instanceArrangement()) {
                Agreement agreement = new Agreement();
                agreement.setGeneralAgreementId(agr.generalAgreementId());
                agreement.setNumber(agr.number());
                agreement.setTppProduct(product.get());
                var agreesmentRes = agreesmentRepository.save(agreement);
                agreementIdList.add(String.valueOf(agreesmentRes.getId()));
            }


        }

        ProductResponse productResponse = new ProductResponse(
                String.valueOf(productId),
                productIdRegisterList,
                agreementIdList
        );

        return productResponse;
    }
}
