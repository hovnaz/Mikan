package am.mikan.mikan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titleUs;
    private String titleRu;
    private String titleHy;
    private String descriptionUs;
    private String descriptionRu;
    private String descriptionHy;
    private String image;
}
