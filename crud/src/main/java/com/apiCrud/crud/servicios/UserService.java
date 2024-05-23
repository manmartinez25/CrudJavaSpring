/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiCrud.crud.servicios;

import com.apiCrud.crud.models.UserModel;
import com.apiCrud.crud.repositories.IUserRepositorio;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*En la presente pestaña se definieron los métodos para acceder al paque IUserRepositorio, la cual se encarga de
la comunicación con la base de datos, por lo tanto aqui se desarrolló la lógica para efectuar los procedimientos 
propios del CRUD*/
@Service
public class UserService {
    
    @Autowired
    IUserRepositorio userRepositorio;
    
    /*El siguiente método permite visualizar todos los objetos UserModel que hay en la base de datos,
    haciendo uso del método findAll del repositorio de Spring*/
    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepositorio.findAll();
    }
    
    /*El siguiente método permite guardar un objeto UserModel en la base de datos,
    haciendo uso del método save del repositorio de Spring*/
    public UserModel saveUser(UserModel user){
        return userRepositorio.save(user);
    }
    
    /*El siguiente método permite visualizar un objeto UserModel que se encuentra en la base de datos mediante el id,
    haciendo uso del método findById del repositorio de Spring*/
    public Optional <UserModel> getById(Long id){
        return userRepositorio.findById(id);
    }
    
    /*El siguiente método permite actualizar un objeto UserModel en la base de datos,
    haciendo uso del método save del repositorio de Spring, primero obteniendo el objeto
    que ya existía y posteriormente guardando los cambios*/
    public UserModel updateById(UserModel request, Long id){
        UserModel user = userRepositorio.findById(id).get();
        
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setEspecialidad(request.getEspecialidad());
        user.setCiudad(request.getCiudad());
        
        return userRepositorio.save(user);
    }
    
    /*El siguiente método permite borrar un objeto UserModel en la base de datos mediante su id,
    haciendo uso del método deleteById del repositorio de Spring*/
    public Boolean deleteUser(Long id){
        try{
            userRepositorio.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    } 
    
}
