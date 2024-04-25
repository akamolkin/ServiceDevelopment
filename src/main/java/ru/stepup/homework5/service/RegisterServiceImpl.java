package ru.stepup.homework5.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.homework5.dto.RegisterRequest;
import ru.stepup.homework5.dto.RegisterResponse;
import ru.stepup.homework5.entity.TppProductRegister;
import ru.stepup.homework5.enums.States;
import ru.stepup.homework5.exception.ExemplNotFoundException;
import ru.stepup.homework5.exception.FindDublException;
import ru.stepup.homework5.repo.AccountRepository;
import ru.stepup.homework5.repo.TppProductRegisterRepository;
import ru.stepup.homework5.repo.TppRefProductRegisterTypeRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private final TppProductRegisterRepository registerRepository;
    @Autowired
    private final TppRefProductRegisterTypeRepository registerTypeRepository;
    @Autowired
    private final AccountRepository accountRepository;

    @Override
    public RegisterResponse addProductRegister(RegisterRequest registerRequest) {

        var checkProductRegisterDubl = registerRepository.findByProductIdAndType(registerRequest.instanceId(), registerRequest.registryTypeCode());
        if (checkProductRegisterDubl.isPresent()) {
            throw new FindDublException("Параметр registryTypeCode тип регистра "
                            + registerRequest.registryTypeCode()
                    + " уже существует для ЭП с ИД "
                            + checkProductRegisterDubl.get().getId()
            );
        }


        var registerType = registerTypeRepository.findByValue(registerRequest.registryTypeCode());
        if (registerType.isEmpty()) {
            throw new ExemplNotFoundException(
                    "Код Продукта "
                            + registerRequest.registryTypeCode()
                    + " не найдено в Каталоге продуктов для данного типа Регистра"
            );
        }

        var account = accountRepository.getAccount(
            registerRequest.branchCode(),
            registerRequest.currencyCode(),
            registerRequest.mdmCode(),
            registerRequest.priorityCode(),
            registerRequest.registryTypeCode()
        );

        TppProductRegister register = new TppProductRegister();
        register.setRegisterType(registerType.get());
        register.setProductId(registerRequest.instanceId());
        register.setAccount(account.get().getId());
        register.setState(States.OPEN);
        register.setAccountNumber(account.get().getAccountNumber());
        var registerRes = registerRepository.save(register);

        RegisterResponse res = new RegisterResponse(String.valueOf(registerRes.getId()));
        return res;
    }
}
