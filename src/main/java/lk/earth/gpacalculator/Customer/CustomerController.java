package lk.earth.gpacalculator.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @GetMapping
    public List<Customer> getAll(){
        return customerDao.findAll();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Integer id){
        Optional<Customer> optionalCustomer=customerDao.findById(id);
        if(optionalCustomer.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found");
        return optionalCustomer.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//hariyata del una nam meka enwa
    public void delete(@PathVariable Integer id){
        if(customerDao.existsById(id)){
            customerDao.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found");
        }

    }

    @PostMapping
    public Customer add(@RequestBody Customer customer){
        return customerDao.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Integer id,@RequestBody Customer customer){
        if(customerDao.existsById(id)){
            customer.setId(id);
            return customerDao.save(customer);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not Found");
    }
}
