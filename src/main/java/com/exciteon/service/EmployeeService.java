package com.exciteon.service;


import com.exciteon.modal.Employee;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class EmployeeService {

    // get all user details in firebase
    public List<Employee> getAllEmployee()throws ExecutionException, InterruptedException{
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<Employee> employees=new ArrayList<>();

        ApiFuture<QuerySnapshot> future = dbFireStore.collection("User").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
            employees.add(queryDocumentSnapshot.toObject(Employee.class));
        }
        return employees;
    }

    // save user details in firebase
    public String addEmployee(Employee employee) {
        Firestore firestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture=firestore.collection("User").document(employee.getUid()).set(employee);
        return "success";
    }
}
