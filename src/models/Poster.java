package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="posters")
@NamedQueries({
    @NamedQuery(
            name="getAllPosters",
            query="select p from Poster as p order by p.id DESC"
            ),
    @NamedQuery(
            name="getPostersCount",
            query="select count(p) from Poster as p"
            ),
    @NamedQuery(
            name="checkRegisteredName",
            query="select count(p) from Poster as  p where p.name=:name"
            ),
    @NamedQuery(
            name="checkLoginNameAndPassword",
            query="select p from Poster as p where p.name=:name and p.password=:pass"
            ),


})
@Entity
public class Poster {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name",nullable=false,unique=true)
    private String name;

    @Column(name="password",length=64,nullable=false)
    private String password;

    @Column(name="created_at",nullable=false)
    private Timestamp created_at;

    @Column(name="updatef_at",nullable=false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
