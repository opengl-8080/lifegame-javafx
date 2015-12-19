package gl8080.lifegame.logic;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * エンティティの基底クラス。
 * <p>
 * このクラスは、全てのエンティティで必要になる ID による一意識別の仕組みを提供します。
 * <p>
 * エンティティはデータベースに永続化された後も、再び JVM 上にロードされれれば
 * インスタンスを一意に識別できる必要があります。<br>
 * このクラスは、そのための一意識別の仕組みを提供します。
 * <p>
 * 一意識別には、データベースで発行されるプライマリキーを使用します。<br>
 * このクラスの {@code equals()} メソッドは、設定されたプライマリキーを元にオブジェクトが同じかどうかを判定します。
 * また、 {@code hashCode()} メソッドもプライマリキーを元にハッシュ値を生成します。<br>
 * これによって、エンティティのライフサイクル全体を通して、インスタンスの識別を可能とします。
 * <p>
 * しかし、この方法には問題があり完全ではありません。<br>
 * インスタンスがデータベースに保存される前は、プライマリキーに値が設定されていません。
 * よって、プライマリキーの値だけでは、オブジェクトの識別ができないケースが発生します。
 * <p>
 * このクラスは、この問題に対してプライマリキーが未設定かどうかで識別の方法を切り替えることで対応しています。<br>
 * つまり、プライマリキーが未設定の場合は、オブジェクトを別物と判定し、ハッシュ値はデフォルトの実装を利用して生成します。
 * <p>
 * しかし、この方法にも欠点があります。<br>
 * コレクション・フレームワークを使っている場合（特に {@code HashMap} や {@code HashSet} など）、
 * コレクションにオブジェクトをセットしたあとに永続化を実行すると、プライマリキーが設定されるため
 * {@code equals()} メソッドや {@code hashCode()} がコレクションにセットした時とは異なる結果を返すようになります。<br>
 * 結果、ハッシュ値を使ってオブジェクを識別しているようなコレクションは、登録したオブジェクトを見失うことになります。
 * <p>
 * つまり、このクラスを継承して作成するエンティティは、同値性を利用するコレクションには使用できません。<br>
 * 同値性を気にしない場合に限り、このクラスを継承したクラスをコレクションで使用できます。
 * 
 * @see <a href="http://stackoverflow.com/questions/5031614/the-jpa-hashcode-equals-dilemma">The JPA hashCode() / equals() dilemma | stackoverflow</a>
 * @see <a href="http://www.onjava.com/pub/a/onjava/2006/09/13/dont-let-hibernate-steal-your-identity.html?page=1">Don't Let Hibernate Steal Your Identity | O'Reilly Media</a>
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    protected AbstractEntity() {}
    
    public Long getId() {
        return this.id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        
        AbstractEntity other = (AbstractEntity) o;
        
        if (other.getId() == null) {
            return false;
        } else {
            return this.getId() == other.getId();
        }
    }
    
    @Override
    public int hashCode() {
        if (this.getId() == null) {
            return super.hashCode();
        } else {
            return this.id.hashCode();
        }
    }
    
    /**
     * @deprecated このコンストラクタは単体テスト用です。
     */
    @Deprecated
    AbstractEntity(Long id) {
        this.id = id;
    }
}
