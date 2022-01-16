package com.aec.autoeletricacebola.service.servico;

import java.sql.Date;
import java.util.List;

import com.aec.autoeletricacebola.model.Servico;

public interface ServicoService {

    Servico findById(Long id);
    List <Servico> findAll();
    List <Servico> findByServicesUnfinished();

    List<Servico> findByCarPlate(String plate);
    List<Servico> findByCarPlateWhereDateFrom(String plate, Date dateStart);
    List<Servico> findByCarPlateWhereDateUntil(String plate, Date dateEnd);
    List<Servico> findByCarPlateWhereDateInterval(String plate, Date dateStart, Date dateEnd);

    List<Servico> findByNameAndPhone(String name, String phone);
    List<Servico> findByNameAndPhoneWhereDateFrom(String name, String phone, Date dateStart);
    List<Servico> findByNameAndPhoneWhereDateUntil(String name, String phone, Date dateEnd);
    List<Servico> findByNameAndPhoneWhereDateInterval(String name, String phone, Date dateStart, Date dateEnd);

    List<Servico> findByPhone(String phone);
    List<Servico> findByPhoneWhereDateFrom(String phone, Date dateStart);
    List<Servico> findByPhoneWhereDateUntil(String phone, Date dateEnd);
    List<Servico> findByPhoneWhereDateInterval(String phone, Date dateStart, Date dateEnd);

    List<Servico> findByName(String name);
    List<Servico> findByNameWhereDateFrom(String name, Date dateStart);
    List<Servico> findByNameWhereDateUntil(String name, Date dateEnd);
    List<Servico> findByNameWhereDateInterval(String name, Date dateStart, Date dateEnd);

    List<Servico> findByDateFrom(Date dateStart);
    List<Servico> findByDateUntil(Date dateEnd);
    List <Servico> findByDateInterval(Date dateStart, Date dateEnd);

    Servico save(Servico servico);
}
