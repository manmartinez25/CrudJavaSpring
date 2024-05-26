/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apiCrud.crud.servicios;

import com.apiCrud.crud.models.UserModel;
import com.apiCrud.crud.repositories.IUserRepositorio;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*En la presente pestaña se definieron los métodos para acceder al paquete IUserRepositorio, el cual se encarga de
la comunicación con la base de datos, por lo tanto aqui se desarrolló la lógica para efectuar los procedimientos 
propios del CRUD y las validaciones correspondientes a las variables que componen el objeto*/
@Service
public class UserService {

    @Autowired
    IUserRepositorio userRepositorio;

    public static Boolean validarLongitud(UserModel user) {
        if (user.getFirstName().length() > 30 || user.getLastName().length() > 30 || user.getEspecialidad().length() > 20 || user.getCiudad().length() > 15) {
            return false;
        }
        return true;
    }

    public static Boolean validarEmail(UserModel user) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(user.getEmail());
        if (matcher.find() == true) {
            return true;
        }
        return false;
    }

    /*El siguiente método permite visualizar todos los objetos UserModel que hay en la base de datos,
    haciendo uso del método findAll del repositorio de Spring*/
    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepositorio.findAll();
    }

    /*El siguiente método permite guardar un objeto UserModel en la base de datos,
    haciendo uso del método save del repositorio de Spring*/
    public String saveUser(UserModel user) {
        try {
            if (validarLongitud(user) == true && validarEmail(user) == true) {
                userRepositorio.save(user);
            } else {
                return "Datos incorrectos";
            }
        } catch (Exception e) {
            return "Error al guardar el registro";
        }
        return "Agregado exitosamente";
    }

    /*El siguiente método permite visualizar un objeto UserModel que se encuentra en la base de datos mediante el id,
    haciendo uso del método findById del repositorio de Spring*/
    public Optional<UserModel> getById(Long id) {
        return userRepositorio.findById(id);
    }

    /*El siguiente método permite actualizar un objeto UserModel en la base de datos,
    haciendo uso del método save del repositorio de Spring, primero obteniendo el objeto
    que ya existía y posteriormente guardando los cambios*/
    public String updateById(UserModel request, Long id) {
        if (validarLongitud(request) == true && validarEmail(request) == true) {
            UserModel user = userRepositorio.findById(id).get();

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setEspecialidad(request.getEspecialidad());
            user.setCiudad(request.getCiudad());

            userRepositorio.save(user);
            return "Actualización exitosa";
        }
        return "Datos incorrectos";

    }

    /*El siguiente método permite borrar un objeto UserModel en la base de datos mediante su id,
    haciendo uso del método deleteById del repositorio de Spring*/
    public Boolean deleteUser(Long id) {
        try {
            userRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
