package digital.softwareshinobi.projectchimba.usherrule.robot;

import digital.softwareshinobi.projectchimba.usherrule.service.BrokerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@RequestMapping("trading-robot")
@Configuration
@EnableScheduling
public class UsherRuleRobot {

    private final Logger logger = LoggerFactory.getLogger(UsherRuleRobot.class);

//    @Autowired
//    private RestTemplate restTemplate;
    private final List marketAnalysisReportList = new ArrayList();

    private Integer countMarketEvaluations = 0;

    private final Set<Integer> targetMinuteValueSet = new HashSet<>(Arrays.asList(0, 8, 16, 24, 32, 40, 48, 56));

    @Bean
    public RestTemplate getRestTemplate() {

        return new RestTemplate();

    }

    @GetMapping("")
    public String fetchLandingPage() {

        return "The Usher Rule Trading Robot";

    }

    @GetMapping("/health-check")
    public String doHealthCheck() {

        return "UsherRuleTradingRobot is UP";

    }

    @Scheduled(fixedRate = 1000 * 60)
    @SuppressWarnings("unused")
    private void performMarketAnalysis() {

        System.out.println("enter > performMarketAnalysis()");

        Date currentDate = new Date();

        System.out.println("date / " + currentDate);

        Map marketAnalysisReport = ReportStuff(currentDate);

        Map triggerJustificationReport = new HashMap();

        triggerJustificationReport.put("targetMinute", targetMinuteValueSet);

        triggerJustificationReport.put("actualMinute", currentDate.getMinutes());

        Boolean doTrigger = false;

        if (this.targetMinuteValueSet.contains(currentDate.getMinutes())) {

            doTrigger = true;

            triggerJustificationReport.put("description", "current minute number [" + currentDate.getMinutes() + "] and "
                    + "target minute number  [" + this.targetMinuteValueSet + "] DO MATCH");

            executeOnTrigger();

        } else {

            triggerJustificationReport.put("description", "current minute number [" + currentDate.getMinutes() + "] and "
                    + "target minute number  [" + this.targetMinuteValueSet + "] DO NOT match");

        }

        marketAnalysisReport.put("triggerJustificationReport", triggerJustificationReport);

        marketAnalysisReport.put("doTrigger", doTrigger);

        System.out.println("executionReport" + marketAnalysisReport);

        marketAnalysisReportList.add(marketAnalysisReport);

        System.out.println("exit < performMarketAnalysis()");

        countMarketEvaluations++;

    }

    public Map ReportStuff(Date currentDate) {

        Map marketAnalysisReport = new HashMap();

        marketAnalysisReport.put("broker", "napkin-exchange");

        marketAnalysisReport.put("security", "dione");

        marketAnalysisReport.put("countMarketEvaluations", countMarketEvaluations);

        marketAnalysisReport.put("candle", "dione");

        marketAnalysisReport.put("bid", "bid");

        marketAnalysisReport.put("ask", "bid");

        marketAnalysisReport.put("time", currentDate.getTime());

        marketAnalysisReport.put("date", currentDate.toString());

        return marketAnalysisReport;

    }

    private void executeOnTrigger() {

        System.out.println("enter > executeOnTrigger()");

        Map buySecurities = BrokerService.buySecurities();

        System.out.println("exit < executeOnTrigger()");

    }

    @GetMapping("/analysis-report/")
    public Map fetchMarketAnalysisReportHistory() {

        Map postExecutionReport = new HashMap();

        postExecutionReport.put("size", marketAnalysisReportList.size());

        postExecutionReport.put("executionReports", marketAnalysisReportList);

        return postExecutionReport;

    }

}
