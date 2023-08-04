package com.humber.eap.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.humber.eap.models.Course;
import com.humber.eap.models.Student;

@Component
public class StudentServiceClient {
	RestTemplate restTemplate = new RestTemplate();
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceClient.class);

	// get all student records, create and update API
	String apiUrlForGetCreateUpdate = "http://localhost:8080/api/student";
	// delete student API
	final int studentIdToDelete = 2;
	String apiUrlForDelete = "http://localhost:8080/api/student/" + studentIdToDelete;

	public void callApiRunLogger() throws InterruptedException {
//		GET API CALL
		ResponseEntity<Student[]> studentGetResponse = restTemplate.getForEntity(apiUrlForGetCreateUpdate,
				Student[].class);
		Student[] students = studentGetResponse.getBody();
		LOGGER.info("Calling get API");
		LOGGER.info(studentGetResponse.getStatusCode().toString());
		LOGGER.info("Length of student records - {}", students.length);
		LOGGER.info("--------------------------------------------------------");

//		POST API CALL
		// student record to post
		Student studentToPost = Student.builder().roll(101).s_name("Josh").age(34)
				.course(Course.builder().courseName("Engineering").creditHours(5).build()).build();
		// Set up headers for JSON content
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Student> postRequestEntity = new HttpEntity<>(studentToPost, headers);

		ResponseEntity<String> studenPostResponse = restTemplate.exchange(apiUrlForGetCreateUpdate, HttpMethod.POST,
				postRequestEntity, String.class);
		LOGGER.info("Calling post API");
		LOGGER.info(studenPostResponse.getStatusCode().toString());
		LOGGER.info("--------------------------------------------------------");

//		DELETE API CALL
		ResponseEntity<String> studenDeleteResponse = restTemplate.exchange(apiUrlForDelete, HttpMethod.DELETE, null,
				String.class);
		LOGGER.info("Calling delete API");
		LOGGER.info(studenDeleteResponse.getStatusCode().toString());
		LOGGER.info("Response body: {}", studenDeleteResponse.getBody());
		LOGGER.info("--------------------------------------------------------");

//		UPDATE API CALL
		// student record to post
		Student studentToUpdate = Student.builder().id(1).roll(855).s_name("Simul Bista").age(20)
				.course(Course.builder().courseName("JAVA").creditHours(4).build()).build();
		HttpEntity<Student> putRequestEntity = new HttpEntity<>(studentToUpdate, headers);

		ResponseEntity<String> studenUpdateResponse = restTemplate.exchange(apiUrlForGetCreateUpdate, HttpMethod.PUT,
				putRequestEntity, String.class);
		LOGGER.info("Calling update API");
		LOGGER.info(studenUpdateResponse.getStatusCode().toString());
		LOGGER.info("Response body: {}", studenUpdateResponse.getBody());

	}

}
