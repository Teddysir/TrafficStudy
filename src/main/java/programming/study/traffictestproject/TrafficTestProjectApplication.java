package programming.study.traffictestproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import programming.study.traffictestproject.dto.RequestCreatPostDtoNotRecord;
import programming.study.traffictestproject.dto.RequestCreatePostDto;

@SpringBootApplication
public class TrafficTestProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficTestProjectApplication.class, args);

        RequestCreatPostDtoNotRecord normalClass1 = new RequestCreatPostDtoNotRecord("name", "content");
        RequestCreatPostDtoNotRecord normalClass2 = new RequestCreatPostDtoNotRecord("name", "content");
        System.out.println(normalClass1.equals(normalClass2) + " <<< 일반 클래스");

        RequestCreatePostDto recordDto1 = new RequestCreatePostDto("name", "content");
        RequestCreatePostDto recordDto2 = new RequestCreatePostDto("name", "content");
        System.out.println(recordDto1.equals(recordDto2) + " <<< Record 클래스");

    }

}
