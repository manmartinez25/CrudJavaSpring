/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apiCrud.crud.repositories;

import com.apiCrud.crud.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*En la presente pestaña se hace uso del paquete de repositorio de Spring el cual permite la interacción entre la aplicación 
y un banco de datos a través de una interfaz*/
@Repository
public interface IUserRepositorio extends JpaRepository<UserModel, Long>{
    
}
