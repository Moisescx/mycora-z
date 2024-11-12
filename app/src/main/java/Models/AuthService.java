package Models;


import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AuthService {
    private FirebaseAuth mAuth;

    public AuthService() {
        mAuth = FirebaseAuth.getInstance();
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(String email, String password, final AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            callback.onSuccess("Registro exitoso: " + user.getEmail());
                        } else {
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    // Método para iniciar sesión de usuario
    public void iniciarSesion(String email, String password, final AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            callback.onSuccess("Inicio de sesión exitoso: " + user.getEmail());
                        } else {
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    // Interfaz para manejar los resultados
    public interface AuthCallback {
        void onSuccess(String message);
        void onFailure(String message);
    }

}

