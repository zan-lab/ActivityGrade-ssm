package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.GradeDao;
import com.zanlab.grade.dao.JudgeDao;
import com.zanlab.grade.dao.PlayerDao;
import com.zanlab.grade.dao.RuleDao;
import com.zanlab.grade.domain.*;
import com.zanlab.grade.service.AnalyseService;
import com.zanlab.grade.utils.CommonUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service("analyseService")
public class AnalyseServiceImpl implements AnalyseService {

    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private JudgeDao judgeDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private RuleDao ruleDao;
    @Override
    public List<Player> getPlayerOrder(Integer activityid) {
        return playerDao.findListByActivityidOrderScore(activityid);
    }


    @Override
    public List<Player> getPlayerFairOrder(Integer activityid) {
        return playerDao.findListByActivityidOrderByFariScore(activityid);
    }

    //生成选手-评委表
    @Override
    public void exportPlayerJudge(Activity act, ServletOutputStream out) {
        List<Player>players=playerDao.findListByActivityid(act.getId());
        List<Judge>judges=judgeDao.findListByActivityid(act.getId());
        List<Grade>grades=gradeDao.findListByActivityid(act.getId());

        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("选手-评委表");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 1; i <= judges.size(); i++) {
                hssfCell = row.createCell(i);//列索引
                hssfCell.setCellValue("评委"+i);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            //根据选手的先后顺序来排名而不是成绩

            //第六步，从1行开始写数据
            for (int i = 1; i <= players.size(); i++) {
                Player nowPlayer=players.get(i-1);
                row = hssfSheet.createRow(i);
                //0列写入选手名
                row.createCell(0).setCellValue(nowPlayer.getName());
                //从1列开始写入数据
                List<Grade>playergrades=new ArrayList<>();
                //该选手的所有打分
                for(Grade g:grades) {
                    if(g.getPlayerid().equals(nowPlayer.getId()))playergrades.add(g);
                }
                //循环赋值，从1到size单元格
                for(int j=1;j<=judges.size();j++){
                    double value=0;
                    //从选手的所有得分中找，找到对应的评委的得分，顺序按照评委的先后顺序
                    for (Grade g:playergrades) {
                        if(judges.get(j-1).getId().equals(g.getJudgeid())){
                            value=g.getPlayerscore();
                            break;
                        }
                    }
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0))){
                        row.createCell(j).setCellValue(value);
                    }

                }
            }
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();

        }
    }


    //生成选手-规则表
    @Override
    public void exportPlayerRule(Activity act, ServletOutputStream out) {
        List<Player>players=playerDao.findListByActivityid(act.getId());
        //List<Grade>grades=gradeDao.findListByActivityid(act.getId());
        List<Rule>rules=ruleDao.findListByActivityid(act.getId());
        //外层player，内层10个分数
        List<double[]>playerrulegrades=new ArrayList<>();
        //为了更快，不用反射了，空间换时间
        for(Player player:players){
            //建立选手10个评分的double数组
            double[] rulegrade=new double[10];
            //找到这个选手的相关所有评分
            List<Grade>playergrades=gradeDao.findListByPlayerid(player.getId());

            //通过对每个rule字段的相加，之后求平均，再放进去
            List<Double>temp=new ArrayList<>();
            for(Grade g:playergrades){
                temp.add(g.getRule1());
            }
            rulegrade[0]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule2());
            }
            rulegrade[1]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule3());
            }
            rulegrade[2]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule4());
            }
            rulegrade[3]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule5());
            }
            rulegrade[4]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule6());
            }
            rulegrade[5]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule7());
            }
            rulegrade[6]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule8());
            }
            rulegrade[7]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule9());
            }
            rulegrade[8]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            for(Grade g:playergrades){
                temp.add(g.getRule10());
            }
            rulegrade[9]= CommonUtils.calculateAverage(temp,0);
            temp.clear();

            playerrulegrades.add(rulegrade);
        }
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("选手-规则表");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 1; i<=rules.size(); i++) {
                hssfCell = row.createCell(i);//列索引
                hssfCell.setCellValue(rules.get(i-1).getName());//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            //根据选手的先后顺序来排名而不是成绩

            //第六步，从1行开始写数据
            for (int i = 1; i <= playerrulegrades.size(); i++) {
                Player nowPlayer=players.get(i-1);
                double[]nowGrades=playerrulegrades.get(i-1);
                row = hssfSheet.createRow(i);
                //0列写入选手名
                row.createCell(0).setCellValue(nowPlayer.getName());
                //从1列开始写入数据
                //循环赋值，从1到size单元格
                for(int j=1;j<=rules.size();j++){
                    double value=nowGrades[j-1];
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0))){
                        row.createCell(j).setCellValue(value);
                    }

                }
            }
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    //打分原始数据
    @Override
    public void exportRawData(Activity act, ServletOutputStream out) {
        List<Player>players=playerDao.findListByActivityid(act.getId());
        List<Judge>judges=judgeDao.findListByActivityid(act.getId());
        List<Rule>rules=ruleDao.findListByActivityid(act.getId());

        //定义每行数据
        List<Grade>grades=new ArrayList<>();
        Grade emptygrade=new Grade();
        for(Player player:players){
            for(Judge judge:judges){
                Grade grade=gradeDao.findByJidandPid(judge.getId(),player.getId());
                //如果有评分就添加评分
                if(grade!=null)grades.add(grade);
                //否则添加一个空行
                else grades.add(emptygrade);
            }
        }
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("原始数据表");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            row.createCell(0).setCellValue("选手名");
            row.createCell(1).setCellValue("评委名");
            for (int i = 1; i <= rules.size(); i++) {
                //从第三个开始写规则名
                hssfCell = row.createCell(i+1);//列索引
                hssfCell.setCellValue(rules.get(i-1).getName());//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            //写姓名，评委名，成绩

            //记录写道第几行了
            int nowth=1;
            //第六步，从1行开始写数据
            for (Player player:players) {
                for(Judge judge:judges){
                    row = hssfSheet.createRow(nowth);
                    //前两列名字写好
                    row.createCell(0).setCellValue(player.getName());
                    row.createCell(1).setCellValue(judge.getName());

                    Grade grade=grades.get(nowth-1);
                    //开始写每个规则的成绩
                    Double value=grade.getRule1();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(2).setCellValue(value);
                    value=grade.getRule2();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(3).setCellValue(value);
                    value=grade.getRule3();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(4).setCellValue(value);
                    value=grade.getRule4();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(5).setCellValue(value);
                    value=grade.getRule5();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(6).setCellValue(value);
                    value=grade.getRule6();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(7).setCellValue(value);
                    value=grade.getRule7();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(8).setCellValue(value);
                    value=grade.getRule8();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(9).setCellValue(value);
                    value=grade.getRule9();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(10).setCellValue(value);
                    value=grade.getRule10();
                    if(value==null)continue;
                    if(!(Double.doubleToLongBits(value)==Double.doubleToLongBits(0)))row.createCell(11).setCellValue(value);
                    //新行
                    nowth++;
                }
                //0列写入选手名
            }
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();

        }
    }

}



