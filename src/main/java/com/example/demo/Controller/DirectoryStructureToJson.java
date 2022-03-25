
package com.example.demo.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**Directory Structure to json*/

public class DirectoryStructureToJson{




    public static Node getNode(File node){
        if(node.isDirectory()){
            return new Node(node.getName(),node.getAbsolutePath(),"directory", getDirList( node));
        }else{
            return new Node(node.getName(),node.getAbsolutePath(),"file",null);
        }
    }

   
    public static List<Node> getDirList(File node){
        List<Node> nodeList=new ArrayList<>();
        for(File n : node.listFiles()){
            nodeList.add(getNode(n));
        }
        return nodeList;
    }

    public static class Node {
        private String name;
        private String location;
        private String type;
     

        public Node() { }

        public Node(String name, String location, String type, List<Node> nodeList) {
            this.name = name;
            this.location = location;
            this.type = type;
          
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        
    }

}

