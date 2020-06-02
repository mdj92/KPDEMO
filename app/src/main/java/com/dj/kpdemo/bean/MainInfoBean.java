package com.dj.kpdemo.bean;

import java.util.List;

/**
 * @author dj
 * @description
 * @Date 2020/6/2
 */
public class MainInfoBean {


    /**
     * data : {"amount":3194,"countOfPayByCard":16,"userConsumptionRanking":[{"userId":"00938","amount":60},{"userId":"00561","amount":58},{"userId":"00081","amount":50},{"userId":"00951","amount":44},{"userId":"00916","amount":28}],"countOfPayByMobile":92,"orderCount":108,"dishSalesRanking":[{"name":"米饭","classIdx":42,"count":63,"image":"data:image/jpeg;base64,[B@1fe2571a"},{"name":"水蒸蛋","classIdx":14,"count":7,"image":"data:image/jpeg;base64,[B@3291267"},{"name":"干锅土豆片","classIdx":41,"count":7,"image":"data:image/jpeg;base64,[B@23bdd760"},{"name":"尖椒豆腐","classIdx":122,"count":5,"image":"data:image/jpeg;base64,[B@21851a49"},{"name":"红烧狮子头","classIdx":125,"count":5,"image":"data:image/jpeg;base64,[B@2c52d3e4"}],"salesInfo":[{"day":"2020-06-02","amount":3194,"orderCount":108}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount : 3194.0
         * countOfPayByCard : 16
         * userConsumptionRanking : [{"userId":"00938","amount":60},{"userId":"00561","amount":58},{"userId":"00081","amount":50},{"userId":"00951","amount":44},{"userId":"00916","amount":28}]
         * countOfPayByMobile : 92
         * orderCount : 108
         * dishSalesRanking : [{"name":"米饭","classIdx":42,"count":63,"image":"data:image/jpeg;base64,[B@1fe2571a"},{"name":"水蒸蛋","classIdx":14,"count":7,"image":"data:image/jpeg;base64,[B@3291267"},{"name":"干锅土豆片","classIdx":41,"count":7,"image":"data:image/jpeg;base64,[B@23bdd760"},{"name":"尖椒豆腐","classIdx":122,"count":5,"image":"data:image/jpeg;base64,[B@21851a49"},{"name":"红烧狮子头","classIdx":125,"count":5,"image":"data:image/jpeg;base64,[B@2c52d3e4"}]
         * salesInfo : [{"day":"2020-06-02","amount":3194,"orderCount":108}]
         */

        private double amount;
        private float countOfPayByCard;
        private float countOfPayByMobile;
        private int orderCount;
        private List<UserConsumptionRankingBean> userConsumptionRanking;
        private List<DishSalesRankingBean> dishSalesRanking;
        private List<SalesInfoBean> salesInfo;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public float getCountOfPayByCard() {
            return countOfPayByCard;
        }

        public void setCountOfPayByCard(float countOfPayByCard) {
            this.countOfPayByCard = countOfPayByCard;
        }

        public float getCountOfPayByMobile() {
            return countOfPayByMobile;
        }

        public void setCountOfPayByMobile(float countOfPayByMobile) {
            this.countOfPayByMobile = countOfPayByMobile;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public List<UserConsumptionRankingBean> getUserConsumptionRanking() {
            return userConsumptionRanking;
        }

        public void setUserConsumptionRanking(List<UserConsumptionRankingBean> userConsumptionRanking) {
            this.userConsumptionRanking = userConsumptionRanking;
        }

        public List<DishSalesRankingBean> getDishSalesRanking() {
            return dishSalesRanking;
        }

        public void setDishSalesRanking(List<DishSalesRankingBean> dishSalesRanking) {
            this.dishSalesRanking = dishSalesRanking;
        }

        public List<SalesInfoBean> getSalesInfo() {
            return salesInfo;
        }

        public void setSalesInfo(List<SalesInfoBean> salesInfo) {
            this.salesInfo = salesInfo;
        }

        public static class UserConsumptionRankingBean {
            /**
             * userId : 00938
             * amount : 60.0
             */

            private String userId;
            private double amount;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }
        }

        public static class DishSalesRankingBean {
            /**
             * name : 米饭
             * classIdx : 42
             * count : 63
             * image : data:image/jpeg;base64,[B@1fe2571a
             */

            private String name;
            private int classIdx;
            private int count;
            private String image;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getClassIdx() {
                return classIdx;
            }

            public void setClassIdx(int classIdx) {
                this.classIdx = classIdx;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class SalesInfoBean {
            /**
             * day : 2020-06-02
             * amount : 3194.0
             * orderCount : 108
             */

            private String day;
            private double amount;
            private int orderCount;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public int getOrderCount() {
                return orderCount;
            }

            public void setOrderCount(int orderCount) {
                this.orderCount = orderCount;
            }
        }
    }
}
