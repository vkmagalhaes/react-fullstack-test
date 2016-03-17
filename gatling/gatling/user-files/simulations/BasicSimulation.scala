package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object Home {

  val browse = exec(http("Home") // let's give proper names, as they are displayed in the reports
    .get("/"))
    .pause(7)
    .exec(http("About")
    .get("/about"))
    .pause(2)
    .exec(http("Contact")
    .get("/contact"))
    .pause(3)
}

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://new-catalog-test.sa-east-1.elasticbeanstalk.com")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation").exec(Home.browse)

  setUp(
    scn.inject(rampUsers(10000) over (100 seconds))
  ).protocols(httpConf)
}
