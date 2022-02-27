package com.aec.autoeletricacebola.service.servico;

import java.sql.Date;
import java.util.List;

import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public List <Servico> findAll() {
        return servicoRepository.findAll();
    }

    @Override
    public List <Servico> findByServicesUnfinished() {
        return servicoRepository.findByServicesUnfinished();
    }

    @Override
    public List <Servico> findServicesByClientId(Long id) {
        return this.servicoRepository.findServicesByClientId(id);
    }

    @Override
    public Servico findById(Long id) {
        return servicoRepository.findById(id).get();
    }

    @Override
    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public List <Servico> findByCarPlate(String plate) {
        return this.servicoRepository.findByCarPlate(plate);
    }

    @Override
    public List <Servico> findByCarPlateWhereDateFrom(String plate, Date dateStart) {
        return this.servicoRepository.findByCarPlateWhereDateFrom(plate, dateStart);
    }

    @Override
    public List <Servico> findByCarPlateWhereDateUntil(String plate, Date dateEnd) {
        return this.servicoRepository.findByCarPlateWhereDateUntil(plate, dateEnd);
    }

    @Override
    public List <Servico> findByCarPlateWhereDateInterval(String plate, Date dateStart, Date dateEnd) {
        return this.servicoRepository.findByCarPlateWhereDateInterval(plate, dateStart, dateEnd);
    }

    @Override
    public List <Servico> findByNameAndPhone(String name, String phone) {
        return this.servicoRepository.findByNameAndPhone(name, phone);
    }

    @Override
    public List <Servico> findByNameAndPhoneWhereDateFrom(String name, String phone, Date dateStart) {
        return this.servicoRepository.findByNameAndPhoneWhereDateFrom(name, phone, dateStart);
    }

    @Override
    public List <Servico> findByNameAndPhoneWhereDateUntil(String name, String phone, Date dateEnd) {
        return this.servicoRepository.findByNameAndPhoneWhereDateUntil(name, phone, dateEnd);
    }

    @Override
    public List <Servico> findByNameAndPhoneWhereDateInterval(String name, String phone, Date dateStart, Date dateEnd) {
        return this.servicoRepository.findByNameAndPhoneWhereDateInterval(name, phone, dateStart, dateEnd);
    }

    @Override
    public List <Servico> findByPhone(String phone) {
        return this.servicoRepository.findByPhone(phone);
    }

    @Override
    public List <Servico> findByPhoneWhereDateFrom(String phone, Date dateStart) {
        return this.servicoRepository.findByPhoneWhereDateFrom(phone, dateStart);
    }

    @Override
    public List <Servico> findByPhoneWhereDateUntil(String phone, Date dateEnd) {
        return this.servicoRepository.findByPhoneWhereDateUntil(phone, dateEnd);
    }

    @Override
    public List <Servico> findByPhoneWhereDateInterval(String phone, Date dateStart, Date dateEnd) {
        return this.servicoRepository.findByPhoneWhereDateInterval(phone, dateStart, dateEnd);
    }

    @Override
    public List <Servico> findByName(String name) {
        return this.servicoRepository.findByName(name);
    }

    @Override
    public List <Servico> findByNameWhereDateFrom(String name, Date dateStart) {
        return this.servicoRepository.findByNameWhereDateFrom(name, dateStart);
    }

    @Override
    public List <Servico> findByNameWhereDateUntil(String name, Date dateEnd) {
        return this.servicoRepository.findByNameWhereDateUntil(name, dateEnd);
    }

    @Override
    public List <Servico> findByNameWhereDateInterval(String name, Date dateStart, Date dateEnd) {
        return this.servicoRepository.findByNameWhereDateInterval(name, dateStart, dateEnd);
    }

    @Override
    public List <Servico> findByDateFrom(Date dateStart) {
        return this.servicoRepository.findByDateFrom(dateStart);
    }

    @Override
    public List <Servico> findByDateUntil(Date dateEnd) {
        return this.servicoRepository.findByDateUntil(dateEnd);
    }

    @Override
    public List <Servico> findByDateInterval(Date dateStart, Date dateEnd) {
        return this.servicoRepository.findByDateInterval(dateStart, dateEnd);
    }

}
