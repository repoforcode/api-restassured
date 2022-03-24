package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import service.PostsService;

public final class PostsAPITests extends BaseTest {
	
	private static final Logger log = LogManager.getLogger(PostsAPITests.class);
	
	private PostsAPITests() {};	
	

	@Test
	public void getAllPosts() {
		log.info("validating Get call :: all posts");
		System.out.println(new PostsService().verifyResponseGetAllPosts().size());
		assertNotNull(new PostsService().verifyResponseGetAllPosts());
	}

	@Test
	public void postPosts() {
		log.info("validating Post call :: ");
		assertEquals(new PostsService().verifyPostCall().statusCode(), 201);

	}

	@Test
	public void patchPosts() {
		log.info("validating Patch call :: ");
		assertEquals(new PostsService().verifyPatchCall(5).getStatusCode(),200);

	}
	
	@Test
	public void putPosts() {
		log.info("validating Put call :: ");
		assertEquals(new PostsService().verifyPutCall(5).getStatusCode(),200);

	}
	
	@Test
	public void deletePosts() {
		log.info("validating Delete call :: ");
		new PostsService().verifyDeleteCall(5);

	}
	
	@Test
	public void CRUDPosts() {
		//get all posts and sorted out get next ID to be generated
		PostsService service = new PostsService();
		int idTObeCreated = service.getNewIDForPost()+1;
		log.info("highest posts id created :: "+(idTObeCreated-1));
		log.info("next Post call making for id :: "+idTObeCreated);
		//make a post call
		service.verifyPostCall();
		//make a put call particular ID
		log.info("Put call for id :: "+idTObeCreated);
		service.verifyPutCall(idTObeCreated);
		//make a patch call for particular next ID
		log.info("Patch call for id :: "+idTObeCreated);
		service.verifyPatchCall(idTObeCreated);		
		//make a delete call
		log.info("Delete call for id :: "+idTObeCreated);
		service.verifyDeleteCall(idTObeCreated);
		//make a single ID get call
		log.info("Validating resource not found for :: "+idTObeCreated);
		assertEquals(service.verifySingleGetCall(idTObeCreated).getStatusCode(),404);

	}

}
