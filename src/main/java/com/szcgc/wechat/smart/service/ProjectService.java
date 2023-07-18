package com.szcgc.wechat.smart.service;

import com.szcgc.wechat.smart.entity.Projectdetail;
import com.szcgc.wechat.smart.entity.ProjectdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @Author: chenxinli
 * @Date: 2020/4/17 16:55
 * @Description:
 */

@Service
public class ProjectService {

    @Autowired
    private ProjectdetailRepository projectdetailRepository;


    public Long countUnLoan(String cellphone,String businessclass,double amount_min,double amount_max,int page){
        return getUnLoan(cellphone,businessclass,amount_min,amount_max,page).getTotalElements();
    }

    public Long countLoaning(String cellphone,String businessclass,double amount_min,double amount_max,int page){
        return getLoaning(cellphone,businessclass,amount_min,amount_max,page).getTotalElements();
    }

    public Long countLoaned(String cellphone,String businessclass,double amount_min,double amount_max,int page){
        return getLoaned(cellphone,businessclass,amount_min,amount_max,page).getTotalElements();
    }

    public Page<Projectdetail> getUnLoan(String cellphone, String businessclass, double amount_min, double amount_max, int page){
        return projectdetailRepository.getUnloan(cellphone,businessclass,amount_min,amount_max,dftPage(page,10));
    }

    public Page<Projectdetail> getLoaning(String cellphone,String businessclass,double amount_min,double amount_max,int page){
        return projectdetailRepository.getloaning(LocalDate.now().toString(),cellphone,businessclass,amount_min,amount_max,dftPage(page,10));
    }

    public Page<Projectdetail> getLoaned(String cellphone,String businessclass,double amount_min,double amount_max,int page){
        return projectdetailRepository.getloaned(LocalDate.now().toString(),cellphone,businessclass,amount_min,amount_max,dftPage(page,10));
    }

    public List<String> listBusinessClass(String cellphone){
        return projectdetailRepository.listBusinessClass(cellphone);
    }

    public void removeAllProjectDetails(){
        projectdetailRepository.deleteAll();
    }

    public Iterable<Projectdetail> addProjectDetails(List<Projectdetail> projectdetails){
        return projectdetailRepository.saveAll(projectdetails);
    }

    public Projectdetail getProjectDetailByMainid(int mainid){
        return projectdetailRepository.findProjectdetailByMainid(mainid);
    }

    public Projectdetail getProjectDetailByProjectCode(String projectCode){
        return projectdetailRepository.findProjectdetailByProjectcode(projectCode);
    }


    public Iterable<Projectdetail> updateProjectDetailForOA(List<Projectdetail> projectdetails){
        projectdetailRepository.deleteAll();
        return projectdetailRepository.saveAll(projectdetails);
    }

    private static final Sort DFTSORT = Sort.by(Sort.Direction.DESC, "id");

    private Pageable dftPage(int page, int size) {
        checkArgument(page >= 0);
        checkArgument(size > 0);
        return PageRequest.of(page, size, DFTSORT);
    }
}
