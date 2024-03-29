package digital.softwareshinobi.projectchimba.usherrule.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Configuration
@EnableScheduling
public class BrokerService {

    private final Logger logger = LoggerFactory.getLogger(BrokerService.class);

    @Autowired
    private RestTemplate restTemplate;

    public static final String base = "https://apis.napkinexchange.softwareshinobi.digital";

    public static final String ROBOT_ACCOUNT_NAME = "sasuke" + new Date().getTime();

    public static final String ROBOT_EMAIL_ADDRESS = "sasuke@uchiha.digital";

    public String createTradingAccount() {

        String brokerEndpointURL = base + "/account/create";

        System.out.println("enter > create-trading-account");

        MultiValueMap<String, String> brokerRequest = new LinkedMultiValueMap<>();

        Map requestHeaders = new HashMap<String, String>();

        requestHeaders.put("Content-Type", "application/json");

        brokerRequest.setAll(requestHeaders);

        Map requestBody = new HashMap();

        requestBody.put("username", ROBOT_ACCOUNT_NAME);

        requestBody.put("email", ROBOT_EMAIL_ADDRESS);

        HttpEntity<?> brokerHttpRequest = new HttpEntity<>(requestBody, brokerRequest);

        ResponseEntity<?> brokerResponse = new RestTemplate().postForEntity(brokerEndpointURL, brokerHttpRequest, String.class);

        System.out.println("brokerResponse / " + brokerResponse.getBody());

        System.out.println("exit < create-trading-account");

        return "OK";

    }

    public Map detailTradingAccount() {

        String brokerEndpointURL = base + "/account/" + ROBOT_ACCOUNT_NAME;

        System.out.println("enter > detail-trading-account");

        MultiValueMap<String, String> brokerRequest = new LinkedMultiValueMap<>();

        Map requestHeaders = new HashMap<String, String>();

        requestHeaders.put("Content-Type", "application/json");

        brokerRequest.setAll(requestHeaders);

        Map requestBody = new HashMap();

        requestBody.put("username", ROBOT_ACCOUNT_NAME);

        requestBody.put("email", ROBOT_EMAIL_ADDRESS);

        HttpEntity<?> brokerHttpRequest = new HttpEntity<>(requestBody, brokerRequest);

        ResponseEntity<?> brokerResponse = new RestTemplate().postForEntity(brokerEndpointURL, brokerHttpRequest, Map.class);

        System.out.println("brokerResponse / " + brokerResponse.getBody());

        System.out.println("exit < detail-trading-account");

        return (Map) brokerResponse.getBody();

    }

    public Map fundTradingAccount() {

        String brokerEndpointURL = base + "/account/deposit";

        System.out.println("enter > fund-trading-account");

        MultiValueMap<String, String> brokerRequest = new LinkedMultiValueMap<>();

        Map requestHeaders = new HashMap<String, String>();

        requestHeaders.put("Content-Type", "application/json");

        brokerRequest.setAll(requestHeaders);

        Map requestBody = new HashMap();

        requestBody.put("username", ROBOT_ACCOUNT_NAME);

        requestBody.put("amountToAdd", 4_000_000);

        HttpEntity<?> brokerHttpRequest = new HttpEntity<>(requestBody, brokerRequest);

        ResponseEntity<?> brokerResponse = new RestTemplate().postForEntity(brokerEndpointURL, brokerHttpRequest, Map.class);

        System.out.println("brokerResponse / " + brokerResponse.getBody());

        System.out.println("exit < fund-trading-account");

        return (Map) brokerResponse.getBody();

    }

    public static Map buySecurities() {

        String brokerEndpointURL = base + "/inventory/buy/market";

        System.out.println("enter > buy-securities");

        MultiValueMap<String, String> brokerRequest = new LinkedMultiValueMap<>();

        Map requestHeaders = new HashMap<String, String>();

        requestHeaders.put("Content-Type", "application/json");

        brokerRequest.setAll(requestHeaders);

        Map requestBody = new HashMap();

        requestBody.put("username", ROBOT_ACCOUNT_NAME);

        requestBody.put("ticker", "PANDORA");

        requestBody.put("sharesToBuy", 4444);

        HttpEntity<?> brokerHttpRequest = new HttpEntity<>(requestBody, brokerRequest);

        ResponseEntity<?> brokerResponse = new RestTemplate().postForEntity(brokerEndpointURL, brokerHttpRequest, Map.class);

        System.out.println("brokerResponse / " + brokerResponse.getBody());

        System.out.println("exit < buy-securities");

        return (Map) brokerResponse.getBody();

    }

}

//    //   @RequestMapping(value = "/fetch-signals")
//    private String fetchBrokerSignals() {
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//        return restTemplate.exchange("https://api.napkinexchange.softwareshinobi.digital/candlestick/DIONE", HttpMethod.GET, entity, String.class).getBody();
//
//    }
//    @GetMapping("")
//    public String fetchLandingPage() {
//
//        return "The Usher Rule Trading Robot";
//
//    }
//
//    @GetMapping("/health-check")
//    public String doHealthCheck() {
//
//        return "UsherRuleTradingRobot is UP";
//
//    }
/*
        
        curl -X POST -H "Content-Type: application/json" -d '{"username":"SasukeReloaded","email":"linuxize@example.com"}' http://localhost:9999/account/create
        
        
 */

 /*
        
        curl -X POST -H "Content-Type: application/json" -d '{"username":"SasukeReloaded","email":"linuxize@example.com"}' http://localhost:9999/account/create
        
        
 */
