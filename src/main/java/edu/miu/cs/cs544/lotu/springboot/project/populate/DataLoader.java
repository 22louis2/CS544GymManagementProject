package edu.miu.cs.cs544.lotu.springboot.project.populate;

import edu.miu.cs.cs544.lotu.springboot.project.entity.Address;
import edu.miu.cs.cs544.lotu.springboot.project.enums.EquipmentStatus;
import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageStatus;
import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageType;
import edu.miu.cs.cs544.lotu.springboot.project.enums.Role;
import edu.miu.cs.cs544.lotu.springboot.project.repository.Member;
import edu.miu.cs.cs544.lotu.springboot.project.repository.Trainer;
import edu.miu.cs.cs544.lotu.springboot.project.repository.GymClass;
import edu.miu.cs.cs544.lotu.springboot.project.repository.WorkoutSession;
import edu.miu.cs.cs544.lotu.springboot.project.repository.Subscription;
import edu.miu.cs.cs544.lotu.springboot.project.service.Credential;
import edu.miu.cs.cs544.lotu.springboot.project.repository.Equipment;
import edu.miu.cs.cs544.lotu.springboot.project.repository.ClassRegistration;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final Member memberRepository;
    private final Trainer trainerRepository;
    private final GymClass gymClassRepository;
    private final WorkoutSession workoutSessionRepository;
    private final Subscription subscriptionRepository;
    private final Credential credentialService;
    private final Equipment equipmentRepository;
    private final ClassRegistration classRegistrationRepository;

    public DataLoader(
            Member memberRepository,
            Trainer trainerRepository,
            GymClass gymClassRepository,
            WorkoutSession workoutSessionRepository,
            Subscription subscriptionRepository,
            Credential credentialService,
            Equipment equipmentRepository,
            ClassRegistration classRegistrationRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
        this.gymClassRepository = gymClassRepository;
        this.workoutSessionRepository = workoutSessionRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.credentialService = credentialService;
        this.equipmentRepository = equipmentRepository;
        this.classRegistrationRepository = classRegistrationRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create Credentials
        edu.miu.cs.cs544.lotu.springboot.project.entity.Credential credential1 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Credential();
        credential1.setUsername("john123");
        credential1.setPassword("secure1");
        credential1.setRole(Role.MEMBER);

        edu.miu.cs.cs544.lotu.springboot.project.entity.Credential credential2 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Credential();
        credential2.setUsername("emma456");
        credential2.setPassword("secure2");
        credential2.setRole(Role.MEMBER);

        edu.miu.cs.cs544.lotu.springboot.project.entity.Credential trainerCredential = new edu.miu.cs.cs544.lotu.springboot.project.entity.Credential();
        trainerCredential.setUsername("trainerMike");
        trainerCredential.setPassword("trainer123");
        trainerCredential.setRole(Role.TRAINER);

        List<String> existingUsers = List.of("john123", "emma456", "trainerMike");
        boolean allUsersMissing = existingUsers.stream()
                .allMatch(username -> credentialService.findByUsername(username).isEmpty());

        if(allUsersMissing)
        {
            var managedCredential1 = credentialService.register(credential1);
            var managedCredential2 = credentialService.register(credential2);
            var managedTrainedCredential = credentialService.register(trainerCredential);

            // Create Address
            Address address1 = new Address();
            address1.setStreet("1 Active Avenue");
            address1.setCity("Intensity City");
            address1.setState("Dynamic State");
            address1.setZip("11111");
            address1.setCountry("USA");

            Address address2 = new Address();
            address2.setStreet("2 Power Lane");
            address2.setCity("Peakville");
            address2.setState("Motivation Land");
            address2.setZip("22222");
            address2.setCountry("USA");

            // Create Members
            edu.miu.cs.cs544.lotu.springboot.project.entity.Member member1 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Member();
            member1.setFirstName("John");
            member1.setLastName("Doe");
            member1.setEmail("john@example.com");
            member1.setPhone("1234567890");
            member1.setAddress(address1);
            member1.setJoinedDate(LocalDate.now().minusMonths(3));
            member1.setCredential(managedCredential1);

            edu.miu.cs.cs544.lotu.springboot.project.entity.Member member2 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Member();
            member2.setFirstName("Emma");
            member2.setLastName("Smith");
            member2.setEmail("emma@example.com");
            member2.setPhone("0987654321");
            member2.setAddress(address2);
            member2.setJoinedDate(LocalDate.now().minusMonths(1));
            member2.setCredential(managedCredential2);

            memberRepository.saveAll(List.of(member1, member2));

            // Create Trainer
            edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer trainer = new edu.miu.cs.cs544.lotu.springboot.project.entity.Trainer();
            trainer.setFirstName("Mike");
            trainer.setLastName("Johnson");
            trainer.setEmail("mike@trainer.com");
            trainer.setPhone("1122334455");
            trainer.setAddress(address1);
            trainer.setSpecialization("CrossFit");
            trainer.setBio("Specialist in CrossFit and HIIT training.");
            trainer.setYearsOfExperience(12);
            trainer.setQualification("CrossFit Level 3 Certified Instructor");
            trainer.setCredential(managedTrainedCredential);

            trainerRepository.save(trainer);

            // Create Subscriptions
            edu.miu.cs.cs544.lotu.springboot.project.entity.Subscription subscription1 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Subscription();
            subscription1.setStartDate(LocalDate.now().minusMonths(3));
            subscription1.setEndDate(LocalDate.now().plusMonths(3));
            subscription1.setPackageStatus(PackageStatus.ACTIVE);
            subscription1.setPackageType(PackageType.BASIC);
            subscription1.setMember(member1);

            edu.miu.cs.cs544.lotu.springboot.project.entity.Subscription subscription2 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Subscription();
            subscription2.setStartDate(LocalDate.now().minusMonths(1));
            subscription2.setEndDate(LocalDate.now().plusMonths(5));
            subscription2.setPackageStatus(PackageStatus.ACTIVE);
            subscription2.setPackageType(PackageType.PREMIUM);
            subscription2.setMember(member2);

            subscriptionRepository.saveAll(List.of(subscription1, subscription2));

            // Create Gym Classes
            edu.miu.cs.cs544.lotu.springboot.project.entity.GymClass yogaClass = new edu.miu.cs.cs544.lotu.springboot.project.entity.GymClass();
            yogaClass.setName("Morning Yoga");
            yogaClass.setDuration(60);
            yogaClass.setCapacity(20);
            yogaClass.setTrainer(trainer);

            edu.miu.cs.cs544.lotu.springboot.project.entity.GymClass bootcampClass = new edu.miu.cs.cs544.lotu.springboot.project.entity.GymClass();
            bootcampClass.setName("Bootcamp Blast");
            bootcampClass.setDuration(90);
            bootcampClass.setCapacity(15);
            bootcampClass.setTrainer(trainer);

            gymClassRepository.saveAll(List.of(yogaClass, bootcampClass));

            // Create Equipment
            edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment yogaMat1 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment();
            yogaMat1.setName("Yoga Mat 1");
            yogaMat1.setEquipmentStatus(EquipmentStatus.AVAILABLE);

            edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment yogaMat2 = new edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment();
            yogaMat2.setName("Yoga Mat 2");
            yogaMat2.setEquipmentStatus(EquipmentStatus.AVAILABLE);

            edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment kettlebell = new edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment();
            kettlebell.setName("Kettlebell");
            kettlebell.setEquipmentStatus(EquipmentStatus.IN_USE);

            edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment treadmill = new edu.miu.cs.cs544.lotu.springboot.project.entity.Equipment();
            treadmill.setName("Treadmill");
            treadmill.setEquipmentStatus(EquipmentStatus.UNDER_MAINTENANCE);

            equipmentRepository.saveAll(List.of(yogaMat1, yogaMat2, kettlebell, treadmill));

            // Create Class Registrations
            edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration registration1 = new edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration();
            registration1.setRegistrationDate(LocalDate.now().minusWeeks(2));
            registration1.setGymClass(yogaClass);
            registration1.setMember(member1);

            edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration registration2 = new edu.miu.cs.cs544.lotu.springboot.project.entity.ClassRegistration();
            registration2.setRegistrationDate(LocalDate.now());
            registration2.setGymClass(bootcampClass);
            registration2.setMember(member2);

            classRegistrationRepository.saveAll(List.of(registration1, registration2));

            // Create Workout Sessions
            edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession session1 = new edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession();
            session1.setDate(LocalDate.of(2023, 11, 5));
            session1.setMember(member1);
            session1.setGymClass(yogaClass);
            session1.setEquipment(List.of(yogaMat1, treadmill));

            edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession session2 = new edu.miu.cs.cs544.lotu.springboot.project.entity.WorkoutSession();
            session2.setDate(LocalDate.of(2023, 11, 10));
            session2.setMember(member2);
            session2.setGymClass(bootcampClass);
            session2.setEquipment(List.of(kettlebell));

            // Link Equipment to their Workout Sessions (update status)
            yogaMat1.setEquipmentStatus(EquipmentStatus.IN_USE);
            yogaMat1.setWorkoutSession(session1);

            treadmill.setWorkoutSession(session1);

            kettlebell.setEquipmentStatus(EquipmentStatus.IN_USE);
            kettlebell.setWorkoutSession(session2);

            workoutSessionRepository.saveAll(List.of(session1, session2));

            System.out.println("Populated all entities successfully!");
        }
    }
}
