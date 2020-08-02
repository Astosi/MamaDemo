package com.example.mamademo.Models;

public class Job {

        private String description;
        private String imageurl;
        private String jobid;
        private String publisher;
        public boolean isFront = true;

        public Job() {
        }

        public Job (String description, String imageurl, String jobid, String publisher) {
            this.description = description;
            this.imageurl = imageurl;
            this.jobid = jobid;
            this.publisher = publisher;

        }

        public  String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getjobid() {
            return jobid;
        }

        public void setjobid(String postid) {
            this.jobid = jobid;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }
    }


