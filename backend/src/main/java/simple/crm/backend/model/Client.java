package simple.crm.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@Entity
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted=false")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;

    private String area;

    private String address;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
