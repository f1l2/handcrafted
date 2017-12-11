package at.f1l2.laboratory.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "A")
public class A {

    @Id
    // @SequenceGenerator(name = "SEQ")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    private Long id;

    private String description;

    @OneToMany(targetEntity = B.class, cascade = CascadeType.ALL)
    private Set<B> bs;
    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<B> getBs() {
        return bs;
    }

    public void setBs(Set<B> bs) {
        this.bs = bs;
    }
}
