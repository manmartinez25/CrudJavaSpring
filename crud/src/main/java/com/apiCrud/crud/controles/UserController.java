/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiCrud.crud.controles;


//Paquetes importados necesarios para ejecutar la aplicación
import com.apiCrud.crud.models.UserModel;
import com.apiCrud.crud.servicios.UserService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*En la presente pestaña se hizo creación de los controladores de la apliación, los cuales incluyen los métodos 
para llamar los métodos del paquete userService desde donde se envian las peticiones a la base de datos*/
@RestController
@RequestMapping("/CrudSpringJava")
public class UserController {
    
    @Autowired
    private UserService userService;
    
       
    /*En el siguiente método se hace llamado para ejecutar el método getUsers() del paquete userService*/
    @GetMapping
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }
    
    /*En el siguiente método se hace llamado para ejecutar el método saveUser() del paquete userService*/
    @PostMapping
    public String saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }
    
    /*En el siguiente método se hace llamado para ejecutar el método getById() del paquete userService*/
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id){
        return this.userService.getById(id);
    }
    
    /*En el siguiente método se hace llamado para ejecutar el método updateById() del paquete userService*/
    @PutMapping(path = "/{id}")
    public String UpdateUserById(@RequestBody UserModel userModel,@PathVariable("id") Long id){
        return this.userService.updateById(userModel, id);
    }
    
    /*En el siguiente método se hace llamado para ejecutar el método deleteUser() del paquete userService*/
    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = (boolean) this.userService.deleteUser(id);
        
        if(ok){
            return "User with id "+ id + " deleted";
        }else{
            return "Error, we have a problem deleting the user with id "+ id;
        }
        
    }
}
