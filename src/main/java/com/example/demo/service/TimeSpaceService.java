package com.example.demo.service;

import com.example.demo.domain.TimeSpaceDTO;
import com.example.demo.domain.ToDoDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.mapper.TimeSpaceMapper;
import com.example.demo.model.Category;
import com.example.demo.model.TimeSpace;
import com.example.demo.repository.TimeSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.OrganizationUtil.fromStringToDate;

@Service
@RequiredArgsConstructor
public class TimeSpaceService {

    private final TimeSpaceRepository timeSpaceRepository;
    private final TimeSpaceMapper timeSpaceMapper;

    public ResponseEntity<String> findTimeByDate(String date)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<TimeSpace> timeSpaces = timeSpaceRepository.findAllByDate(LocalDate.parse(date, formatter));
        Integer time = timeSpaces.stream().map(TimeSpace::getTime).mapToInt(Integer::intValue).sum();
        String result = "\""+(int) Math.floor(time/60)+":"+(time%60)+"\"";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<TimeSpaceDTO>> findAllTimeSpacesByDate(String date)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new ResponseEntity<>(timeSpaceMapper.fromEntityListToDTOList(timeSpaceRepository.findAllByDate(LocalDate.parse(date, formatter))), HttpStatus.OK);
    }

    public ResponseEntity<List<TimeSpaceDTO>> findAllTimeSpacesByToDo(Long toDoId)  {
        return new ResponseEntity<>(timeSpaceMapper.fromEntityListToDTOList(timeSpaceRepository.findAllByToDoId(toDoId)), HttpStatus.OK);
    }

    public ResponseEntity<List<TimeSpaceDTO>> findAllTimeSpacesByTask(Long taskId)  {
        return new ResponseEntity<>(timeSpaceMapper.fromEntityListToDTOList(timeSpaceRepository.findAllByTaskId(taskId)), HttpStatus.OK);
    }

    public ResponseEntity<?> getLastTimeSpace (){
        TimeSpace lastTimeSpace = timeSpaceRepository.findTopByOrderByDateAndIdDesc();

        return new ResponseEntity<>(timeSpaceMapper.fromEntityToDTO(lastTimeSpace), HttpStatus.OK);
    }

    public ResponseEntity<List<TimeSpaceDTO>> findAllTimeSpaces()  {
        return new ResponseEntity<>(timeSpaceMapper.fromEntityListToDTOList(timeSpaceRepository.findByOrderByDateAndIdDesc()), HttpStatus.OK);
    }

    public ResponseEntity<?> createTimeSpace (TimeSpaceDTO timeSpaceDTO)  {
        TimeSpace timeSpace = timeSpaceMapper.fromDTOtoEntity(timeSpaceDTO);
        /*Optional<TimeSpace> optionalTimeSpace = timeSpaceRepository.findByToDoAndDate(timeSpace.getToDo(), timeSpace.getDate());
        if (optionalTimeSpace.isPresent()){
            timeSpace.setTime(timeSpace.getTime() + optionalTimeSpace.get().getTime());
            timeSpace.setId(optionalTimeSpace.get().getId());
        }*/

        timeSpaceRepository.save(timeSpace);

        return new ResponseEntity<>(timeSpace, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateTimeSpace (Long timeSpaceId, TimeSpaceDTO timeSpaceDTO)  {
        if (timeSpaceRepository.existsById(timeSpaceId)){
            TimeSpace timeSpace = timeSpaceMapper.fromDTOtoEntity(timeSpaceDTO);
            timeSpace.setId(timeSpaceId);
            /*Optional<TimeSpace> optionalTimeSpace = timeSpaceRepository.findByToDoAndDate(timeSpace.getToDo(), timeSpace.getDate());
            if (optionalTimeSpace.isPresent()){
                timeSpace.setTime(timeSpace.getTime() + optionalTimeSpace.get().getTime());
                timeSpace.setId(optionalTimeSpace.get().getId());
            }*/

            timeSpaceRepository.save(timeSpace);

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> deleteTimeSpace (Long timeSpaceId){
        if (timeSpaceId!=null && timeSpaceRepository.existsById(timeSpaceId)){
            timeSpaceRepository.deleteById(timeSpaceId);

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

}
