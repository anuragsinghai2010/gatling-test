package simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://httpbin.org");

    ScenarioBuilder scn =
            scenario("Basic Scenario")
                    .exec(
                            http("Get Request")
                                    .get("/get")
                                    .check(status().is(200))
                    );

    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}