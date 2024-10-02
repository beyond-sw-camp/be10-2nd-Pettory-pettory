package com.pettory.pettory.pet.command.domain.aggregate;

import com.pettory.pettory.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "pet_access")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class PetAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petAccessId;

    @CreatedDate
    private LocalDateTime petAccessInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime petAccessUpdateDatetime;
    private LocalDateTime petAccessDeleteDatetime;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    Pet pet;    // // PetAccess -> Pet 단방향 참조

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;  // PetAccess -> User 단방향 참조

    @Enumerated(EnumType.STRING)
    private PetAccessState petAccessState = PetAccessState.ACTIVE;

    public PetAccess(User user, Pet pet) {
        this.user = user;
        this.pet = pet;
    }

    // petAccess 생성하는 메소드
    public static PetAccess createPetAccess(User user, Pet pet) {
        return new PetAccess(user, pet);
    }

    // petAccess의 상태를 삭제로 변경하는 메소드
        this.petAccessState = PetAccessState.DELETE;
        this.petAccessDeleteDatetime = LocalDateTime.now();
    }

}
