package br.com.gabrieladriano.voteapplication.configs;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.gabrieladriano.voteapplication.domain.models.Agenda;
import br.com.gabrieladriano.voteapplication.repositories.AgendaRepository;

//Infelizmente não tive tempo de implementar os streams, queues e consumers do kafka entao resolvi utilizar schedule
//que executa uma certo rotina após um tempo estimado
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private AgendaRepository agendaRepository;

    //a rotina compara o tempo de criacao com o atual
    @Scheduled(fixedDelay = 1000)
    public void scheduleForClosingAgenda(Long id) {
        List<Agenda> toClose = agendaRepository.findAll();
        toClose = toClose.stream().filter(a -> (new Date().getTime() - a.getTime_of_creation()) >= 60000L && a.getOpen() == true).collect(Collectors.toList());
        toClose.forEach(a -> a.setOpen(false));
        agendaRepository.saveAll(toClose);
    }
}
