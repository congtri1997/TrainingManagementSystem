package edu.hcmuaf.tms.data;

import com.github.javafaker.Faker;

import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.repository.TraineeRepository;
import edu.hcmuaf.tms.service.impl.ProgrammingLanguageService;
import edu.hcmuaf.tms.service.impl.RoleService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.time.ZoneId;
import java.util.Random;

@Component
@Slf4j
class TraineeInitializer {
    private static final int NUMBER_TO_GENERATE = 10;
    
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private ProgrammingLanguageService programmingLanguageService;
    
    
    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        log.info("generating {} random trainee", NUMBER_TO_GENERATE);
        roleService.addIfNotExist("ROLE_TRAINEE");
        programmingLanguageService.addIfNotExist("java");
        programmingLanguageService.addIfNotExist("c");
        programmingLanguageService.addIfNotExist("c#");
        programmingLanguageService.addIfNotExist("javascript");
        programmingLanguageService.addIfNotExist("python");
        programmingLanguageService.addIfNotExist("php");
        
        Random randomGenerator = new Random();
        Faker faker = new Faker();
        for (int i = 1; i <= NUMBER_TO_GENERATE; i++) {
            Trainee trainee = Trainee.builder()
                    .id(i)
                    .userName("trainee"+i)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .enabled(true)
                    .encryptedPassword(encoder.encode("123123"))
                    .role(roleService.findByName("ROLE_TRAINEE"))
                    .birthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .scoreOfToeic(randomGenerator.nextInt((990-10)+1)+10)
                    .education(faker.university().name())
                    .address(faker.address().buildingNumber())
                    .detailsOfExp(faker.chuckNorris().fact())
                    .programmingLanguage(randomGenerator.nextInt(6) == 5 ?  programmingLanguageService.findByName("php") : randomGenerator.nextInt(6) == 4 ?  programmingLanguageService.findByName("python") : randomGenerator.nextInt(6) == 3 ?  programmingLanguageService.findByName("javascript") : randomGenerator.nextInt(6) == 2 ?  programmingLanguageService.findByName("c#") :  randomGenerator.nextInt(6) == 0 ?  programmingLanguageService.findByName("java") : randomGenerator.nextInt(6) == 1 ? programmingLanguageService.findByName("c") : null )
                    .department(faker.superhero().name())
                    .build();
			traineeRepository.save(trainee);
		}
	}

}