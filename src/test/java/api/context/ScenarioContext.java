package api.context;

import io.restassured.response.Response;

public class ScenarioContext {
    private Response latestResponse;

    public Response getLatestResponse(){
        if(latestResponse == null){
            throw new IllegalStateException(
                    "No response is available in the current scenario"
            );
        }

        return latestResponse;
    }

    public void setLatestResponse(Response response){
        this.latestResponse = response;
    }

    public boolean hasResponse(){
        return (latestResponse != null);
    }
}
