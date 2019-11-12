package com.cognizant.fse.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.fse.entity.Project;
@Repository
public interface ProjectDao extends CrudRepository<Project, Long>, ProjectDaoCustom {
	//@Query("SELECT new Project(p.projectId,p.projectName,p.startDate,p.endDate,p.priority,p.user, (select count(t) from Task t where t.project.projectId=p.projectId and t.status != 'CMP') as tasksCount, (select count(t) from Task t where t.project.projectId=p.projectId and t.status = 'CMP') as completedTasks) from Project p")//Sort feature is not works for this as table alias name is taking as t bcz from Task t is first in the query and that alias is taking and adding to sorting field. 
	//public List<Project> getAllProjectsWithTaskCounts();//Sort sort);
	//This example show how to do custom conversion, 1-by using Query annotation with entity constructor, 2-By implementing Custom Repository interface and impl class and extend the custom repository interface with base repository 
	//Both scenarios has been tested and worked well, but custom method the data is not setting for the fields which are declared as @Transiant and another problem is the Sort is not working for @Query.
	//Also there are other options for custom conversions like:Arrtibute Converter-A Converter must implement the javax.persistence.AttributeConverter<X, Y> interface, where X is the class of the entity representation and Y the class of the database representation of the attribute. Additionally a Converter has to be annotated with the javax.persistence.Converter annotation.
	//@Projection also would be used for custom mappings but here it can be used mostly to take less attributes from the entity
	//@SqlResultSetMapping(
   /* name = "AuthorMapping",
    entities = @EntityResult(
            entityClass = Author.class,
            fields = {
                @FieldResult(name = "id", column = "authorId"),
                @FieldResult(name = "firstName", column = "firstName"),
                @FieldResult(name = "lastName", column = "lastName"),
                @FieldResult(name = "version", column = "version")}))
	List<Author> results = this.em.createNativeQuery("SELECT a.id as authorId, a.firstName, a.lastName, a.version FROM Author a", "AuthorMapping").getResultList();
	Query q = em.createNativeQuery(
      "SELECT c.id, c.name, COUNT(o) as orderCount, AVG(o.price) AS avgOrder " +
      "FROM Customer c, Orders o " +
      "WHERE o.cid = c.id " +
      "GROUP BY c.id, c.name",
      "CustomerDetailsResult");

   @SqlResultSetMapping(
       name="CustomerDetailsResult",
       classes={
          @ConstructorResult(
               targetClass=com.acme.CustomerDetails.class,
                 columns={
                    @ColumnResult(name="id"),
                    @ColumnResult(name="name"),
                    @ColumnResult(name="orderCount"),
                    @ColumnResult(name="avgOrder", type=Double.class)
                    }
          )
       }
      )
	
	https://thoughts-on-java.org/result-set-mapping-basics/
*/

}
