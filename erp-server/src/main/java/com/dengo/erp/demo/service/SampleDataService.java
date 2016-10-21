package com.dengo.erp.demo.service;

import com.dengo.erp.config.StaticResourceConfiguration;
import com.dengo.erp.demo.mock.CustomMockMultipartFile;
import com.dengo.erp.model.*;
import com.dengo.erp.model.enums.StatusCandidate;
import com.dengo.erp.model.enums.TypeUser;
import com.dengo.erp.model.enums.TypeVacancy;
import com.dengo.erp.model.tachometer.BreakTachometer;
import com.dengo.erp.model.tachometer.DayTachometer;
import com.dengo.erp.model.tachometer.ScreenTachometer;
import com.dengo.erp.model.tachometer.UserTachometer;
import com.dengo.erp.model.task.Label;
import com.dengo.erp.model.task.Priority;
import com.dengo.erp.model.task.Status;
import com.dengo.erp.model.task.Task;
import com.dengo.erp.repository.*;
import com.dengo.erp.repository.tachometer.BreakTachometerRepository;
import com.dengo.erp.repository.tachometer.UserTachometerRepository;
import com.dengo.erp.repository.task.PriorityRepository;
import com.dengo.erp.repository.task.StatusRepository;
import com.dengo.erp.service.PhotoService;
import com.dengo.erp.service.ProjectService;
import com.dengo.erp.service.VacancyService;
import com.dengo.erp.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * SampleDataService
 * Service that provide populating DB
 *
 * @author Sergey Petrovsky
 */
@Service
public class SampleDataService {

    @Autowired
    PhotoService photoService;

    @Autowired
    ProjectService projectService;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    VacancyService vacancyService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    StepRepository stepRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserTachometerRepository userTachometerRepository;

    @Autowired
    BreakTachometerRepository breakTachometerRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void populateSampleDB() throws IOException, NoSuchAlgorithmException {

        Department sales = new Department("Sales Department");
        Department management = new Department("Management Department");

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        Task task1 = new Task();
        Task task2 = new Task();

        Status available = new Status("available");
        Status busy = new Status("busy");

        Priority highPriority = new Priority("high");
        Priority lowPriority = new Priority("low");

        Label label1 = new Label("high priority");
        Label label2 = new Label("deadline in 2 weeks");

        Project projectOne = new Project();
        projectOne.setName("CRM");
        projectOne.setCustomer("Software Electronics");
        projectOne.setArea("Web");
        projectOne.setStartDate(LocalDate.now());
        projectOne.setProductOwner("Public");

        Project projectTwo = new Project();
        projectTwo.setName("Finger Prints");
        projectTwo.setCustomer("Hardware institution");
        projectTwo.setArea("Security");
        projectTwo.setStartDate(LocalDate.now());
        projectTwo.setProductOwner("Public");

        user1.setBirthday(LocalDate.now());
        user1.setName("Dmitry");
        user1.setLastName("Sheremet");
        user1.setEmail("admin");
        user1.setPassword(encoder.encode("admin"));
        user1.setMessengerLogin("dima_sheremet");
        user1.setPhone("+30991234569");
        user1.setPosition("Middle Java Developer ");
        user1.setType(TypeUser.EMPLOYEE);
//        uploadPhoto("/home/devds/CCRM-DENGO/api-client-server/src/main/webapp/image/Marta_Chupil.jpg", "Marta_Chupil.jpg");
        user1.setPhoto("/images/original/751d26d9991ecd71288da42eac94ab1c.jpg");
        user1.setPreviewPhoto("/images/preview/751d26d9991ecd71288da42eac94ab1c.jpg");

        user2.setBirthday(LocalDate.now());
        user2.setName("Marta");
        user2.setLastName("Chupil");
        user2.setEmail("user");
        user2.setPassword(encoder.encode("user"));
        user2.setMessengerLogin("martha_chupil");
        user2.setPhone("+30997894523");
        user2.setPosition("Senior Web Developer ");
//        uploadPhoto("/home/devds/CCRM-DENGO/api-client-server/src/main/webapp/image/Dmitry_Sheremet.jpg", "Dmitry_Sheremet.jpg");
        user2.setPhoto("/images/original/cc0f091a54ce1a2864b3985c07ad9437.jpg");
        user2.setPreviewPhoto("/images/preview/cc0f091a54ce1a2864b3985c07ad9437.jpg");
        user2.setType(TypeUser.CUSTOMER);

        user3.setBirthday(LocalDate.now());
        user3.setName("Andrii");
        user3.setLastName("Blyznuk");
        user3.setEmail("krasav4k@gmail.com");
        user3.setMessengerLogin("andrii_blyznuk");
        user3.setPhone("+30964106766");
        user3.setPosition("Senior Java Developer ");
        user3.setPhoto("/images/original/751d26d9991ecd71288da42eac94ab1c.jpg");
        user3.setPreviewPhoto("/images/preview/751d26d9991ecd71288da42eac94ab1c.jpg");
        user3.setType(TypeUser.EMPLOYEE);

        priorityRepository.save(highPriority);
        priorityRepository.save(lowPriority);
        statusRepository.save(available);
        statusRepository.save(busy);

        task1.setTitle("Task-1");
        task1.setType("task");
        task1.setStatus(available);
        task1.setPriority(highPriority);
        task1.setLabels(new HashSet(Arrays.asList(label1)));

        task1.setCreateDate(LocalDateTime.now());
        task1.setTitle("Make view");
        task1.setShortDescription("Add base functionality for user interface");
        task1.setDescription("Make authorisation page");

        task2.setTitle("Task-2");
        task2.setType("test");
        task2.setStatus(busy);
        task2.setPriority(lowPriority);
        task1.setLabels(new HashSet(Arrays.asList(label2)));

        task2.setCreateDate(LocalDateTime.now());
        task2.setTitle("Make refactoring");
        task2.setShortDescription("Remove unused imports");
        task2.setDescription("Make code shorter and more readable");

        skillRepository.save(new Skill("JQuery"));
        skillRepository.save(new Skill("Css"));
        skillRepository.save(new Skill("Angular"));
        skillRepository.save(new Skill("Javascript"));
        skillRepository.save(new Skill("Php"));

        String [] arrayStep = {"Interview","Interview 2"};

        vacancyService.save(new Vacancy("JavaScript Developer", TypeVacancy.INACTIVE, arrayStep));
        vacancyService.save(new Vacancy("Php Developer", TypeVacancy.INACTIVE, arrayStep));
        vacancyService.save(new Vacancy("Java Developer", TypeVacancy.CLOSED, arrayStep));
        vacancyService.save(new Vacancy("Angular Developer", TypeVacancy.CLOSED, arrayStep));
        vacancyService.save(new Vacancy("Android Developer", TypeVacancy.OPENED, LocalDate.now(), arrayStep));
        vacancyService.save(new Vacancy("Designer", TypeVacancy.OPENED, LocalDate.now(), arrayStep));
        vacancyService.save(new Vacancy("PM", TypeVacancy.OPENED, LocalDate.now(), arrayStep));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        departmentRepository.save(sales);
        departmentRepository.save(management);
        task1.setAuthor(user1);
        task2.setAuthor(user2);

        User adminS = userRepository.findOne(1L);
        User userS = userRepository.findOne(2L);
        Department salesS = departmentRepository.findOne(1L);
        Department managementS = departmentRepository.findOne(2L);

        task1.setPerformers(new HashSet<>(Arrays.asList(user3, user2)));
        task1.setSpectators(new HashSet<>(Arrays.asList(user1)));
        task2.setPerformers(new HashSet<>(Arrays.asList(user1)));

        projectRepository.save(projectOne);
        projectRepository.save(projectTwo);

        task1.setProject(projectOne);
        task2.setProject(projectTwo);

        taskService.saveTask(task1);
        taskService.saveTask(task2);

        Step step = new Step();
        step.setDate(LocalDate.now());
        step.setTime(LocalTime.now());
        step.addMark(new Mark(9.5, userRepository.findOne(1L), new Comment("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aliquam aut consectetur cupiditate fuga nesciunt non pariatur possimus totam.")));
        step.addMark(new Mark(7.5, userRepository.findOne(2L), new Comment("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aliquam aut consectetur cupiditate fuga nesciunt non pariatur possimus totam.")));
        step.addInterviewer(userRepository.findOne(1L));
        step.addInterviewer(userRepository.findOne(2L));



        Step step1 = new Step();
        step1.setDate(LocalDate.now());
        step1.setTime(LocalTime.now());
        step1.addMark(new Mark(7.1 , userRepository.findOne(2L), new Comment("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aliquam aut consectetur cupiditate fuga nesciunt non pariatur possimus totam.")));
        step1.addInterviewer(userRepository.findOne(2L));


        Step step2 = new Step();
        step2.setDate(LocalDate.now());
        step2.setTime(LocalTime.now());
        step2.addMark(new Mark(2.7, userRepository.findOne(1L), new Comment("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aliquam aut consectetur cupiditate fuga nesciunt non pariatur possimus totam.")));
        step2.addInterviewer(userRepository.findOne(1L));
        step2.addInterviewer(userRepository.findOne(2L));


        Step step3 = new Step();
        step3.setDate(LocalDate.now());
        step3.setTime(LocalTime.now());
        step3.addMark(new Mark(9.9, userRepository.findOne(1L), new Comment("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aliquam aut consectetur cupiditate fuga nesciunt non pariatur possimus totam.")));
        step3.addInterviewer(userRepository.findOne(1L));


        Step step4 = new Step();
        step4.setDate(LocalDate.now());
        step4.setTime(LocalTime.now());
        step4.addMark(new Mark(4.7, userRepository.findOne(1L), new Comment("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aliquam aut consectetur cupiditate fuga nesciunt non pariatur possimus totam.")));
        step4.addInterviewer(userRepository.findOne(1L));



        Step step5 = new Step();
        step5.setDate(LocalDate.now());
        step5.setTime(LocalTime.now());
        step5.addMark(new Mark(5.4, userRepository.findOne(1L), new Comment("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aliquam aut consectetur cupiditate fuga nesciunt non pariatur possimus totam.")));
        step5.addInterviewer(userRepository.findOne(1L));
        step5.addInterviewer(userRepository.findOne(2L));

        User candidate = new User();
        candidate.setName("Andrii");
        candidate.setLastName("Blyzniuk");
        candidate.setEmail("Andriubliznuk@mail.ru");
        candidate.setStatusToCandidate(StatusCandidate.PENDING);
        candidate.setType(TypeUser.CANDIDATE);
        candidate.addStepToCandidate(step);
        candidate.addStepToCandidate(step3);
        candidate.addVacancy(vacancyService.getOne(6L));


        User candidate1 = new User();
        candidate1.setName("Dmytro");
        candidate1.setLastName("Blyzniuk");
        candidate1.setEmail("Dmytrobliznuk@mail.ru");
        candidate1.setStatusToCandidate(StatusCandidate.REJECTED);
        candidate1.setType(TypeUser.CANDIDATE);
        candidate1.addStepToCandidate(step1);
        candidate1.addStepToCandidate(step4);
        candidate1.addVacancy(vacancyService.getOne(6L));


        User candidate2 = new User();
        candidate2.setName("Olexii");
        candidate2.setLastName("Blyzniuk");
        candidate2.setEmail("Olexiibliznuk@mail.ru");
        candidate2.setStatusToCandidate(StatusCandidate.TAKEN);
        candidate2.setType(TypeUser.CANDIDATE);
        candidate2.addStepToCandidate(step2);
        candidate2.addStepToCandidate(step5);
        candidate2.addVacancy(vacancyService.getOne(6L));


        userRepository.save(candidate);
        userRepository.save(candidate1);
        userRepository.save(candidate2);

        Vacancy vacancy = vacancyService.getOne(6L);
        vacancy.setDutiesAndResponsible("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda at aut dignissimos et fugit illum itaque, magnam neque, nulla officiis optio porro quas ratione recusandae rerum sint vel! Aliquam amet animi asperiores autem deleniti dolore doloremque, eum ex hic id illo impedit inventore ipsa iure laboriosam laudantium libero magni minima molestiae molestias natus nesciunt officiis omnis pariatur perferendis placeat quas quidem quis quo rem repellendus sunt temporibus velit veritatis vitae voluptas, voluptate. Adipisci, beatae commodi corporis cumque ducimus eveniet in iusto minima, numquam porro quaerat sunt? Cupiditate, dolore et id iste libero, maiores, nobis quae quibusdam repellat sequi sunt vel!");
        vacancy.setRequiredSkills("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae exercitationem facilis fuga, fugiat ipsam, laudantium magni nisi non nulla odit officia, perferendis soluta? A atque beatae cupiditate dolor dolorem eos hic ipsum iure natus, possimus. Ab distinctio dolor facere fugiat quae ut! Accusamus culpa dolorem quia sunt voluptatum. Ullam, voluptate!");
        vacancy.setWillByAPlus("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex iusto mollitia quas sapiente ut. Adipisci cum dolorum necessitatibus nobis officiis!");
        vacancy.setOffer("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquam amet asperiores beatae deserunt distinctio dolor ea exercitationem fuga minima, molestias nam natus numquam pariatur, possimus quasi quia quisquam recusandae rerum tempora vel. Aliquam animi distinctio ducimus ea iure minima praesentium ratione sit suscipit. Accusamus adipisci alias, aliquam asperiores aspernatur consequatur cum delectus deleniti eaque, est, ipsa laudantium magni quas similique.");
        vacancy.setResponsible(adminS);
        vacancy.addCandidate(userRepository.findOne(4L));
        vacancy.addCandidate(userRepository.findOne(5L));
        vacancy.addCandidate(userRepository.findOne(6L));

        vacancyService.save(vacancy);

        departmentRepository.save(managementS.withEmployee(userS).withDirector(adminS));
        departmentRepository.save(salesS.withEmployee(adminS).withDirector(userS));

        User user4 = userRepository.findOne(1L);
        user4.addStepToInterviewer(stepRepository.findOne(3L));
        user4.addStepToInterviewer(stepRepository.findOne(4L));
        user4.addStepToInterviewer(stepRepository.findOne(5L));
        user4.addStepToInterviewer(stepRepository.findOne(6L));
        userRepository.save(user4);

        User user5 = userRepository.findOne(2L);
        user5.addStepToInterviewer(stepRepository.findOne(3L));
        userRepository.save(user5);

        Step step11 = stepRepository.findOne(1L);
        step11.setVacancy(vacancyService.getOne(6L));
        stepRepository.save(step11);
        Step step12 = stepRepository.findOne(2L);
        step12.setVacancy(vacancyService.getOne(6L));
        stepRepository.save(step12);
        Step step13 = stepRepository.findOne(3L);
        step13.setVacancy(vacancyService.getOne(6L));
        stepRepository.save(step13);
        Step step14 = stepRepository.findOne(4L);
        step14.setVacancy(vacancyService.getOne(6L));
        stepRepository.save(step14);
        Step step15 = stepRepository.findOne(5L);
        step15.setVacancy(vacancyService.getOne(6L));
        stepRepository.save(step15);
        Step step16 = stepRepository.findOne(6L);
        step16.setVacancy(vacancyService.getOne(6L));
        stepRepository.save(step16);

//        addTachometer();

        printResultsFromRepositories();
    }

    private void addTachometer (){

        UserTachometer userTachometer = new UserTachometer(userRepository.getOne(1L).getId());
        userTachometer.setToken("token1");

        DayTachometer dayTachometer = new DayTachometer();
        dayTachometer.setDate(LocalDate.now());
        dayTachometer.setStartTachometer(LocalTime.now());
        dayTachometer.setFinishTachometer(LocalTime.now());
        dayTachometer.setReport("Report1");

        userTachometer.addDayTachometer(dayTachometer);
        userTachometerRepository.save(userTachometer);

        UserTachometer userTachometer1 = new UserTachometer(userRepository.getOne(2L).getId());
        userTachometer1.setToken("token2");

        DayTachometer dayTachometer1 = new DayTachometer();
        dayTachometer1.setDate(LocalDate.now());
        dayTachometer1.setStartTachometer(LocalTime.now());
        dayTachometer1.setFinishTachometer(LocalTime.now());
        dayTachometer1.setReport("Report2");

        DayTachometer dayTachometer2 = new DayTachometer();
        dayTachometer2.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth() - 1));
        dayTachometer2.setStartTachometer(LocalTime.now());
        dayTachometer2.setFinishTachometer(LocalTime.now());
        dayTachometer2.setReport("Report3");

        BreakTachometer breakTachometer = new BreakTachometer();
        breakTachometer.setDescription("Description");
        breakTachometer.setFromTime(LocalTime.now());
        breakTachometer.setToTime(LocalTime.now());
        breakTachometer.setAdd(true);

        BreakTachometer breakTachometer1 = new BreakTachometer();
        breakTachometer1.setDescription("Description1");
        breakTachometer1.setFromTime(LocalTime.now());
        breakTachometer1.setToTime(LocalTime.now());
        breakTachometer1.setAdd(true);

        ScreenTachometer screenTachometer = new ScreenTachometer();
        screenTachometer.setPathToScreen("Path/to/Screen");
        screenTachometer.setPersentKeyboard(68);
        screenTachometer.setPersentMouse(54);
        screenTachometer.setTime(LocalTime.now());
        screenTachometer.setTitleWindow("Chrome");

        ScreenTachometer screenTachometer1 = new ScreenTachometer();
        screenTachometer1.setPathToScreen("Path/to/Second/Screen");
        screenTachometer1.setPersentKeyboard(89);
        screenTachometer1.setPersentMouse(32);
        screenTachometer1.setTime(LocalTime.now());
        screenTachometer1.setTitleWindow("Idea");

        dayTachometer2.addScreenTachometer(screenTachometer);
        dayTachometer2.addScreenTachometer(screenTachometer1);
        dayTachometer2.addBreakTachometer(breakTachometer);
        dayTachometer2.addBreakTachometer(breakTachometer1);

        userTachometer1.addDayTachometer(dayTachometer1);
        userTachometer1.addDayTachometer(dayTachometer2);
        userTachometerRepository.save(userTachometer1);

    }

    // Temp method for printing results from repositories
    private void printResultsFromRepositories() {
        userRepository.findAll().stream()
                .map(user1 -> {
                    String name = user1.getName();
                    Integer size = user1.getDepartments().size();
                    String departments = user1.getDepartments().stream().map(Department::getName).collect(Collectors.joining(","));

                    return name + ": " + size + " " + departments;
                })
                .forEach(System.out::println);

        System.out.println();
        System.out.println("===========================================");
        System.out.println();

        departmentRepository.findAll().stream()
                .map(department -> {
                    String name = department.getName();
                    Integer size = department.getEmployees().size();
                    String departments = department.getEmployees().stream().map(User::getName).collect(Collectors.joining(","));

                    return name + ": " + size + " " + departments;
                })
                .forEach(System.out::println);
    }

    private void uploadPhoto(String path, String fileName) throws IOException, NoSuchAlgorithmException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        photoService.uploadPhotoAndMakePreview(new CustomMockMultipartFile("file", fileName, MediaType.IMAGE_JPEG_VALUE, fileInputStream),
                StaticResourceConfiguration.CUSTOM_STATIC_PATH, 50);

    }
}
