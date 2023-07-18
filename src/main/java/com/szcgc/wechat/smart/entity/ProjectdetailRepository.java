/**
 * Project:szcgcWechatServer
 * File:ProjectdetailRepository.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
public interface ProjectdetailRepository extends PagingAndSortingRepository<Projectdetail, Integer> {

    @Query(value = "select count(distinct t1.id) from projectdetail t1 join keycontactor t2 where t1.customid = t2.customid and (t1.loanamount is null or t1.loanamount=0) and t1.startdate is null and t2.cellphone = ?1",nativeQuery=true)
    public int getUnloanCount(String cellphone);

    @Query(value = "select count(distinct t1.id) from projectdetail t1 join t1.customid t2 where t1.customid = t2.customid and (t1.loanamount>0) and t1.enddate>?1 and t2.cellphone = ?2", nativeQuery=true)
    public int getloaningCount(LocalDateTime currentDate, int customid);

    @Query(value = "select count(distinct t1.id) from projectdetail t1 join keycontactor t2 where t1.customid = t2.customid and t1.enddate<?1 and t2.cellphone = ?2", nativeQuery=true)
    public int getloanedCount(LocalDateTime currentDate,int customid);

    @Query(value = "select distinct t1.* from projectdetail t1 join keycontactor t2 on t1.customid = t2.customid where (t1.loanamount is null or t1.loanamount=0) and t1.startdate is null and t2.cellphone = ?1 and t1.businessclass like %?2% and t1.loanamount between ?3 and ?4 \n#pageable\n",
            countQuery ="select count(distinct t1.id) from projectdetail t1 join keycontactor t2 on t1.customid = t2.customid where (t1.loanamount is null or t1.loanamount=0) and t1.startdate is null and t2.cellphone = ?1 and t1.businessclass like %?2% and t1.loanamount between ?3 and ?4  group by t1.id ",
            nativeQuery=true)
    public Page<Projectdetail> getUnloan(String cellphone, String businessclass, double amount_min, double amount_max, Pageable pageable);

    @Query(value = "select distinct t1.* from projectdetail t1 join keycontactor t2 on t1.customid = t2.customid where (t1.loanamount>0) and t1.enddate>?1 and t2.cellphone = ?2 and t1.businessclass like %?3% and t1.loanamount between ?4 and ?5 \n#pageable\n",
            countQuery = "select count(distinct t1.id) from projectdetail t1 join keycontactor t2 on t1.customid = t2.customid where (t1.loanamount>0) and t1.enddate>?1 and t2.cellphone = ?2 and t1.businessclass like %?3% and t1.loanamount between ?4 and ?5 group by t1.id ",
            nativeQuery=true)
    public Page<Projectdetail> getloaning(String currentDate, String cellphone, String businessclass, double amount_min, double amount_max, Pageable pageable);

    @Query(value = "select distinct t1.* from projectdetail t1 join keycontactor t2 on t1.customid = t2.customid where t1.enddate<?1 and t2.cellphone = ?2 and t1.businessclass like %?3% and t1.loanamount between ?4 and ?5 \n#pageable\n",
            countQuery = "select count(distinct t1.id) from projectdetail t1 join keycontactor t2 on t1.customid = t2.customid where t1.enddate<?1 and t2.cellphone = ?2 and t1.businessclass like %?3% and t1.loanamount between ?4 and ?5  group by t1.id ",
            nativeQuery=true)
    public Page<Projectdetail> getloaned(String currentDate, String cellphone, String businessclass, double amount_min, double amount_max, Pageable pageable);

    @Query(value = "select distinct t1.businessclass from projectdetail t1 join keycontactor t2 on t1.customid = t2.customid where t2.cellphone = ?1", nativeQuery=true)
    public List<String> listBusinessClass(String cellphone);

    public Projectdetail findProjectdetailByMainid(int mainid);

    public Projectdetail findProjectdetailByProjectcode(String projectCode);
}
