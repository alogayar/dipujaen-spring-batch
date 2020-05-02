package es.dipujaen.batch.persistencia.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.dipujaen.batch.models.Upoblacional;

@Repository
public interface IUpoblacionalDao extends PagingAndSortingRepository<Upoblacional, Integer>{

}
