package com.szcgc.assets.controller;

import com.szcgc.assets.entity.AssetInfo;
import com.szcgc.assets.service.AssetService;
import com.szcgc.wechat.controller.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2021-06-07
 */

@Controller
public class AssetsController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = "/assets/info/{var}")
    public String info(@PathVariable("var") String assetCode, Model model) {
        AssetInfo info = assetService.findByCode(assetCode);
        if(info!=null){
            model.addAttribute("assetCode",info.getAssetCode());
            model.addAttribute("assetName",info.getAssetName());
            model.addAttribute("brand",info.getBrand());
            model.addAttribute("productDate",info.getProductDate());
            model.addAttribute("buyDate",info.getBuyDate());
            model.addAttribute("user",info.getUser());
            model.addAttribute("useDate",info.getUseDate());
            model.addAttribute("location",info.getLocation());
            return "szcgc/center/assets/assetInfo";
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/assets/update")
    public String update(@RequestBody List<AssetInfo> assetInfos){
        try {
            for (AssetInfo item : assetInfos) {
                AssetInfo info = assetService.findByCode(item.getAssetCode());
                if (info != null) {
                    info.setAssetName(item.getAssetName());
                    info.setBrand(item.getBrand());
                    info.setBuyDate(item.getBuyDate());
                    info.setProductDate(item.getProductDate());
                    info.setUser(item.getUser());
                    info.setUseDate(item.getUseDate());
                    info.setLocation(item.getLocation());
                    assetService.update(info);
                } else {
                    AssetInfo assetInfo = new AssetInfo();
                    assetInfo.setAssetCode(item.getAssetCode());
                    assetInfo.setAssetName(item.getAssetName());
                    assetInfo.setBrand(item.getBrand());
                    assetInfo.setBuyDate(item.getBuyDate());
                    assetInfo.setProductDate(item.getProductDate());
                    assetInfo.setUser(item.getUser());
                    assetInfo.setUseDate(item.getUseDate());
                    assetInfo.setLocation(item.getLocation());
                    assetService.insert(assetInfo);
                }
            }
            logger.info(LocalDate.now() + "资产信息同步完成");
            return LocalDate.now() + " update success";
        }catch (Exception e){
            logger.info(e.getMessage());
            return e.getMessage();
        }
    }

}
