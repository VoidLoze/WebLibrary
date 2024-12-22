package WebLibrary.WebLibrary.domain;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class BaseEntity {
    private int id;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

