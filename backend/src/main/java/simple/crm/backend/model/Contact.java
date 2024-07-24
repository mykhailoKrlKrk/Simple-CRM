package simple.crm.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@Entity
@Table(name = "contacts")
@SQLDelete(sql = "UPDATE contacts SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted=false")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Client client;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
