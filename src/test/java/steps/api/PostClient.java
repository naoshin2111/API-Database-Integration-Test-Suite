package steps.api;

import constants.Endpoints;
import constants.Parameters;
import models.api.PostResponse;
import io.restassured.response.Response;
import java.util.stream.IntStream;

public class PostClient extends BaseApiClient {

    public boolean arePostsSortedById() {
        Response response = getAllPosts();
        PostResponse[] posts = response.as(PostResponse[].class);

        return IntStream.range(1, posts.length)
                .allMatch(i -> posts[i].getId() > posts[i - 1].getId());
    }

    public Response getAllPosts() {
        return getBaseRequestSpecification()
                .get(Endpoints.POSTS);
    }

    public Response getPostById(int postId) {
        return getBaseRequestSpecification()
                .pathParam(Parameters.ID, postId)
                .get(Endpoints.POST_BY_ID);
    }

    public Response createPost(PostResponse post) {
        return getBaseRequestSpecification()
                .body(post)
                .post(Endpoints.POSTS);
    }
}
