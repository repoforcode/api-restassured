package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import java.lang.reflect.Type;

import constants.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Posts;

public class PostsService {
	
	public void getAllPosts() {
		Response responseAllPost = RestAssured.given().when().basePath(EndPoints.GET_ALL_POSTS).get().andReturn();
		System.out.println(responseAllPost.asString());
		
	}
	
	public List<Posts> verifyResponseGetAllPosts() {
		Response responseAllPost = RestAssured.given().when().basePath(EndPoints.GET_ALL_POSTS).get().andReturn();
		Type type = new TypeReference<List<Posts>>() {}.getType();
		List<Posts> postsList = responseAllPost.as(type);
		return postsList;
	}
	
	public Response verifyPostCall() {
		Posts postObject = new Posts();
		postObject.setAuthor("Ramish");
		postObject.setTitle("Fire");
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.when()
				.body(postObject)
				.post(EndPoints.CREATE_POST)
				.andReturn();
		return response;
	}
	
	public Response verifyPatchCall(int id) {
		Posts postObject = new Posts();
		postObject.setAuthor("GRR");
		postObject.setTitle("Fire and Blood");
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.when()
				.body(postObject)
				.patch(EndPoints.UPDATE_POST)
				.andReturn();
		return response;
	}
	
	public Response verifyPutCall(int id) {
		Posts postObject = new Posts();
		postObject.setAuthor("GRR");
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.when()
				.body(postObject)
				.put(EndPoints.UPDATE_POST)
				.andReturn();
		return response;
	}
	
	public void verifyDeleteCall(int id) {
				RestAssured
				.given()
				.pathParam("id", id)
				.when()
				.delete(EndPoints.DELETE_POST)
				.andReturn();
	}

	public int getNewIDForPost() {
		List<Integer> allIDs = new ArrayList<Integer>();
		for(Posts demo: verifyResponseGetAllPosts()) {
			System.out.println(demo.getId());
			allIDs.add(demo.getId());
		}
		List<Object> allIDsSorted = allIDs.stream().sorted().collect(Collectors.toList());
		return (Integer) allIDsSorted.get(allIDs.size()-1);
	}

	public Response verifySingleGetCall(int id) {
		Response response = RestAssured
				.given()
				.pathParam("id", id)
				.when()
				.basePath(EndPoints.GET_ALL_POSTS)
				.get(EndPoints.GET_SINGLE_POST)
				.andReturn();		
		return response;
		
	}
}
