package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.IChangePasswordDAO;

import java.util.HashMap;
import java.util.Map;

public class MockChangePasswordDAO implements IChangePasswordDAO {


    public HashMap<String,String> getMockUsers(){

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("abc@dal.ca", "abc");
        hashMap.put("xyz@dal.ca", "xyz");
        hashMap.put("mno@dal.ca", "mno");
        hashMap.put("def@dal.ca", "def");
        hashMap.put("pqr@dal.ca", "pqr");

        return hashMap;
    }

    @Override
    public void setPassword(String email, String password) throws Exception {

        HashMap<String,String> mockData = getMockUsers();
        if(!mockData.isEmpty()){
            for (Map.Entry<String, String> map: mockData.entrySet()){
                if(map.getKey().equals(email)){
                    map.setValue("abcdef");
                }else{
                    map.getValue();
                }
            }
        }
    }
}
