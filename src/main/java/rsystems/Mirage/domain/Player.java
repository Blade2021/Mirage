package rsystems.Mirage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;



import static javax.persistence.GenerationType.AUTO;

@Entity(name = "PlayerBase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(
            strategy = AUTO
    )
    private Long id; // PRIMARY KEY | Unique character ID
    private Long dUID;
    private String characterName;
    private String realmName;
    private String characterClass;
    private String characterSpec;
    private int characterItemLevel;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}
