package simple.crm.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@Entity
@Table(name = "tasks")
@SQLDelete(sql = "UPDATE tasks SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted=false")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "contact_id",
            referencedColumnName = "id", insertable = false, updatable = false)
    private Contact contact;

    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    public enum Status {
        OPEN,
        IN_PROGRESS,
        COMPLETED
    }
}
