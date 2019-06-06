package edu.hcmuaf.tms.data;

import com.github.javafaker.Faker;

import edu.hcmuaf.tms.entity.Trainer;
import edu.hcmuaf.tms.repository.TrainerRepository;
import edu.hcmuaf.tms.service.impl.RoleService;
import edu.hcmuaf.tms.service.impl.WorkingTypeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.time.ZoneId;
import java.util.Random;

@Component
@Slf4j
class TrainerInitializer {
    private static final int NUMBER_TO_GENERATE = 10;
    
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private WorkingTypeService workingTypeService;
    
    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        log.info("generating {} random trainer", NUMBER_TO_GENERATE);
        workingTypeService.addIfNotExist("internal");
        workingTypeService.addIfNotExist("external");
        roleService.addIfNotExist("ROLE_TRAINER");
        Random randomGenerator = new Random();
        Faker faker = new Faker();
        for (int i = 1; i <= NUMBER_TO_GENERATE; i++) {
            Trainer trainer = Trainer.builder()
                    .id(i)
                    .userName("trainer"+i)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .enabled(true)
                    .encryptedPassword(encoder.encode("123123"))
                    .role(roleService.findByName("ROLE_TRAINER"))
                    .birthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .workingType(randomGenerator.nextInt(3) == 0 ?  workingTypeService.findByName("internal") : randomGenerator.nextInt(3) == 1 ? workingTypeService.findByName("external") : null )
                    .workingPlace(faker.address().cityName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .email("trainer"+i+ "@example.com").build();
			trainerRepository.save(trainer);
		}
	}

}