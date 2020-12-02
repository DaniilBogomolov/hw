package ru.itis.mongodbdemo.repositories.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.mongodbdemo.models.Employee;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Component
public class EmployeeRepositoryDriverImpl implements EmployeeRepository {

    private MongoCollection<Employee> collection;
    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        CodecRegistry registry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase(databaseName);
        collection = database.getCollection("employee", Employee.class).withCodecRegistry(registry);
    }

    @Override
    public Employee save(Employee entity) {
        BsonValue insertedId = collection.insertOne(entity).getInsertedId();
        entity.set_id(insertedId.asObjectId().getValue().toString());
        return entity;
    }

    @Override
    public Employee update(Employee entity) {
        return collection.findOneAndReplace(eq("_id", entity.get_id()), entity);
    }

    @Override
    public void remove(String id) {
        collection.findOneAndDelete(eq("_id", id));
    }

    @Override
    public Employee findById(String id) {
        return collection.find(eq("_id", id)).first();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : collection.find()) {
            result.add(employee);
        }
        return result;
    }
}
