package top.liyf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.liyf.domain.Job;
import top.liyf.service.OtherServiceInterface;
import top.liyf.util.tag.PageModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**   
 * @Description: 处理职位请求控制器  
 * @version V1.0   
 */

@RequestMapping("/job")
@Controller
public class JobController {

    @Autowired
    @Qualifier("jobServiceImpl")
    OtherServiceInterface service;

    @RequestMapping("/selectJob")
    public String selectJob(Job job, Integer flag, PageModel model, HttpServletRequest request) {

        if (flag == null) {
            request.getSession().setAttribute("search", job.getName());
        } else {
            String search = (String) request.getSession().getAttribute("search");
            job.setName(search);
        }
        String search = job.getName();
        request.setAttribute("search", search);
        List<Job> jobs = service.findJob(job, model);
        request.setAttribute("jobs", jobs);
        request.setAttribute("pageModel", model);

        return "job/job";
    }

    @RequestMapping("/addJob")
    public String addJob(int flag, Job job) {

        if (flag == 1) {
            return "job/showAddJob";
        }
        if (flag == 2) {
            service.addJob(job);
            return "redirect:selectJob";
        }


        return "XXX";
    }

    @RequestMapping("updateJob")
    public String updateJob(int flag, Job job, HttpServletRequest request) {

        if (flag == 1) {
            Job jobById = service.findJobById(job.getId());
            request.setAttribute("job", jobById);
            return "job/showUpdateJob";
        }
        if (flag == 2) {
            service.modifyJob(job);
            return "redirect:selectJob";
        }

        return "XXX";
    }

    @RequestMapping("removeJob")
    public String removeJob(int[] ids) {

        for (int id : ids) {
            service.removeJobById(id);
        }

        return "redirect:selectJob";
    }
}
