package tech.lucasbortolatto.dslearn.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

// teremos apenas task e content, nunca uma lesson "pura"
// como essa classe não pode ser instanciada, ela é abstrata
// para que task e content a utilizem como base, pq ambos são lessons
// o nome disso é "herança total"
@Entity
@Table(name = "tb_lesson")
// essa anotação informa a forma que a herança vai ser mapeada pela JPA
// ou seja, criando um "tabelão" só ou uma tabela pra cada herança
// com muitos campos vale a pena criar tabelas separadas, dai vai da necessidade e interpretação
// entao abaixo, vamos seguir criando tabelas separadas (JOINED = cria 3, uma pra abstract e 2 pras heranças)
// tbm dava pra criar só pras heranças, mas dai repetiria os atributos entre ambas no banco
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    // associação que mostra quais matrículas (usuario + oferta) concluiram essa lição
    @ManyToMany // sem eager, pq nao quero carregar automaticamente sempre
    @JoinTable(name = "tb_lessons_done",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = {     // inverse vai ser 2 ids pq é uma chave composta (usando a classe embedded la)
                    @JoinColumn(name = "user_id"),
                    @JoinColumn(name = "offer_id")
            })
    private Set<Enrollment> enrollmentsDone = new HashSet<>();

    @OneToMany(mappedBy = "lesson")
    private List<Deliver> deliveries = new ArrayList<>();

    public Lesson() {
    }

    public Lesson(Long id, String title, Integer position, Section section) {
        this.id = id;
        this.title = title;
        this.position = position;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Set<Enrollment> getEnrollmentsDone() {
        return enrollmentsDone;
    }

    public List<Deliver> getDeliveries() {
        return deliveries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
