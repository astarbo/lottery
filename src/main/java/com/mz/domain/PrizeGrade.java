package com.mz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="prizegrade")
public class PrizeGrade {
       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private Integer pid;
       /**
        * 几等奖
        */
       private Integer type;
       /**
        * 中了几注
        */
       private Integer typenum;
       /**
        * 中奖金额
        */
       private Long typemoney;
       /**
        * 属于哪一期
        */
       private String code;
 }