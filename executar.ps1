$ErrorActionPreference = 'Stop'
Push-Location -LiteralPath $PSScriptRoot
try {
    $java = 'java'
    $javac = 'javac'
    if ($env:JAVA_HOME -and (Test-Path -LiteralPath "$env:JAVA_HOME\bin\javac.exe")) {
        $java = "$env:JAVA_HOME\bin\java.exe"
        $javac = "$env:JAVA_HOME\bin\javac.exe"
    }
    if (-not (Get-Command $javac -ErrorAction SilentlyContinue)) {
        throw 'JDK nao encontrado. Configure JAVA_HOME ou adicione java e javac ao PATH.'
    }
    New-Item -ItemType Directory -Force -Path out | Out-Null
    $fontes = @(Get-ChildItem -LiteralPath src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
    & $javac --release 11 -encoding UTF-8 -d out $fontes
    if ($LASTEXITCODE -ne 0) { throw 'Falha na compilacao.' }
    & $java '-Dfile.encoding=UTF-8' -cp out br.com.giovanni.loja.Main
    if ($LASTEXITCODE -ne 0) { throw 'Falha na execucao.' }
} finally {
    Pop-Location
}