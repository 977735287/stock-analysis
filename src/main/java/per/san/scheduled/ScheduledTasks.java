package per.san.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import per.san.example.service.IUpDownPercentService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 *
 * @author Sanchar
 * @date 4/12/2019 23:17
 * lastUpdateBy: Sanchar
 * lastUpdateDate: 4/12/2019 23:17
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    IUpDownPercentService iUpDownPercentService;

    @Scheduled(cron = "0 0 18 * * * ")
    public void reportCurrentByCron(){
        logger.info(dateFormat ().format (new Date ()));
        logger.info(iUpDownPercentService.infoUpdate().toString());
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    }

}
