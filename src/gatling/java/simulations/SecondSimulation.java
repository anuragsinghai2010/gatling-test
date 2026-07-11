package simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class SecondSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://dummyjson.com");

    ScenarioBuilder scn =
            scenario("Basic Scenario")
                    .exec(
                            http("Get Request")
                                    .get("/products/1")
                                    .check(status().is(200))
                    ).pause(60);;

    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}