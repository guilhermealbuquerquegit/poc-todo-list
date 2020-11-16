package repository;

import model.Todo;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import java.util.List;

@Repository
public interface TodoRepository extends FullEntityRepository<Todo, Long> {

}